import psycopg2
import psycopg2.extras
import json

from flask import Flask, render_template, request, redirect, url_for

app = Flask(__name__,
            static_folder='static/',
            static_url_path='/static',
            template_folder='templates/')
def get_settings():
    with open('settings.json') as f:
        settings = json.load(f)
        return settings
    return None
app.config['settings'] = get_settings()

def get_database():
    settings = app.config['settings']['database']
    conn = psycopg2.connect(database = settings['name'],
                            user     = settings['user'],
                            password = settings['password'],
                            host     = settings['host'],
                            port     = settings['port'])
    return conn

def get_enderecos():
    conn = get_database()
    cur = conn.cursor()
    cur.execute('SELECT * FROM endereco')
    data = cur.fetchall()
    conn.commit()
    conn.close()
    return data

def get_casas():
    conn = get_database()
    cur = conn.cursor()
    cur.execute('''
    SELECT
        b_armario, n_quartos, n_suites,
        area, n_salas_estar, n_vagas_garagem,
        descricao, numero, rua,
        bairro, cidade, valor_aluguel, id_casa
    FROM imovel
    NATURAL JOIN endereco
    NATURAL JOIN casa;
    ''')
    data = cur.fetchall()
    conn.commit()
    conn.close()
    return data

def insert_endereco(values):
    numero = values['numero']
    rua = values['rua']
    bairro = values['bairro']
    cidade = values['cidade']

    conn = get_database()
    cur = conn.cursor()

    cur.execute('''
        INSERT INTO endereco (numero, rua, bairro, cidade)
        VALUES({}, '{}', '{}', '{}')
    '''.format(numero, rua, bairro, cidade))

    conn.commit()
    conn.close()

def insert_casa(values):
    id_endereco = values['id_endereco']
    b_armario = values['b_armario']
    if(b_armario == "on"):
        b_armario = True
    else:
        b_armario = False
    n_quartos = values['n_quartos']
    n_suites = values['n_suites']
    area = values['area']
    n_salas_estar = values['n_salas_estar']
    n_vagas_garagem = values['n_vagas_garagem']
    descricao = values['descricao']
    valor_aluguel = values['valor_aluguel']
    
    conn = get_database()
    cur = conn.cursor()
    
    cur.execute('''
        INSERT INTO imovel (b_armario, n_quartos, n_suites,
        area, n_salas_estar, n_vagas_garagem, descricao,
        valor_aluguel, id_endereco)
        VALUES({}, {}, {}, {}, {}, {}, '{}', {}, {})
        RETURNING id_imovel;
    '''.format(b_armario, n_quartos, n_suites,
        area, n_salas_estar, n_vagas_garagem, descricao, 
        valor_aluguel, id_endereco))
    
    data = cur.fetchall()
    
    cur.execute('''
        INSERT INTO casa (id_imovel)
        VALUES({})
    '''.format(data[0][0]))

    conn.commit()
    conn.close()


def delete_casa(id_casa):
    conn = get_database()
    cur = conn.cursor()
    
    cur.execute('''
    SELECT
        id_imovel
    FROM imovel
    NATURAL JOIN casa
    WHERE id_casa = {};
    '''.format(id_casa))
    data = cur.fetchall()

    cur.execute('''
        DELETE FROM casa WHERE id_casa = {}
    '''.format(id_casa))

    cur.execute('''
        DELETE FROM casa WHERE id_imovel = {}
    '''.format(data[0][0]))
    
    conn.commit()
    conn.close()

def update():
    return None

@app.route('/endereco', methods=['GET', 'POST'])
def endereco():
    if request.method == 'POST':
        resp = True if insert_endereco(request.form) else False
    
    enderecos = get_enderecos()
    return render_template('endereco.html', enderecos=enderecos)

@app.route('/casa', methods=['GET', 'POST'])
def casa():
    if request.method == 'POST':
        resp = True if insert_casa(request.form) else False

    deleteID = request.args.get('delete')
    if deleteID is not None:
        msg = delete_casa(deleteID)

    casas = get_casas()
    enderecos = get_enderecos()
    return render_template('casa.html', casas=casas, enderecos=enderecos)

@app.route('/')
def index():
    return render_template('index.html')
