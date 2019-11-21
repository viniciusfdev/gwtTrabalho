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

def get_addresses():
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
        descricao, b_quintal, numero, rua,
        bairro, cidade, id_casa
    FROM imovel
    NATURAL JOIN endereco
    NATURAL JOIN casa;
    ''')
    data = cur.fetchall()
    conn.commit()
    conn.close()
    return data

@app.route('/casa')
def casa():
    casas = get_casas()
    return render_template('casa.html', casas=casas)

@app.route('/')
def index():
    return render_template('index.html')
