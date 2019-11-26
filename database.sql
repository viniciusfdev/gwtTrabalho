-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.1-beta
-- PostgreSQL version: 10.0
-- Project Site: pgmodeler.com.br
-- Model Author: ---


-- Database creation must be done outside an multicommand file.
-- These commands were put in this file only for convenience.
-- -- object: new_database | type: DATABASE --
-- -- DROP DATABASE IF EXISTS new_database;
-- CREATE DATABASE new_database
-- ;
-- -- ddl-end --
-- 

-- object: public.imovel | type: TABLE --
-- DROP TABLE IF EXISTS public.imovel CASCADE;
CREATE TABLE public.imovel(
	b_armario boolean DEFAULT FALSE,
	n_quartos smallint,
	n_suites smallint,
	area double precision,
	n_salas_estar smallint,
	n_vagas_garagem smallint,
	descricao varchar,
	valor_aluguel double precision,
	id_endereco integer,
	id_imovel serial NOT NULL,
	CONSTRAINT pk_imovel PRIMARY KEY (id_imovel)
	
);
-- ddl-end --
ALTER TABLE public.imovel OWNER TO postgres;
-- ddl-end --

-- object: public.apartamento | type: TABLE --
-- DROP TABLE IF EXISTS public.apartamento CASCADE;
CREATE TABLE public.apartamento(
	n_andar smallint,
	valor_condominio double precision DEFAULT 0,
	b_portaria boolean DEFAULT FALSE,
	n_salas_jantar smallint,
	n_apartamento smallint,
	id_imovel integer NOT NULL,
	id_apartamento serial NOT NULL,
	CONSTRAINT pk_apartamento PRIMARY KEY (id_apartamento)

);
-- ddl-end --
ALTER TABLE public.apartamento OWNER TO postgres;
-- ddl-end --

-- object: public.casa | type: TABLE --
-- DROP TABLE IF EXISTS public.casa CASCADE;
CREATE TABLE public.casa(
	id_casa serial NOT NULL,
	id_imovel integer NOT NULL,
	CONSTRAINT pk_casa PRIMARY KEY (id_casa)

);
-- ddl-end --
ALTER TABLE public.casa OWNER TO postgres;
-- ddl-end --

-- object: public.endereco | type: TABLE --
-- DROP TABLE IF EXISTS public.endereco CASCADE;
CREATE TABLE public.endereco(
	numero smallint,
	rua varchar,
	cidade varchar,
	bairro varchar,
	id_endereco serial NOT NULL,
	CONSTRAINT id_endereco PRIMARY KEY (id_endereco)

);
-- ddl-end --
ALTER TABLE public.endereco OWNER TO postgres;
-- ddl-end --

-- object: imovel_fk | type: CONSTRAINT --
-- ALTER TABLE public.apartamento DROP CONSTRAINT IF EXISTS imovel_fk CASCADE;
ALTER TABLE public.apartamento ADD CONSTRAINT imovel_fk FOREIGN KEY (id_imovel)
REFERENCES public.imovel (id_imovel) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: apartamento_uq | type: CONSTRAINT --
-- ALTER TABLE public.apartamento DROP CONSTRAINT IF EXISTS apartamento_uq CASCADE;
ALTER TABLE public.apartamento ADD CONSTRAINT apartamento_uq UNIQUE (id_imovel);
-- ddl-end --

-- object: imovel_fk | type: CONSTRAINT --
-- ALTER TABLE public.casa DROP CONSTRAINT IF EXISTS imovel_fk CASCADE;
ALTER TABLE public.casa ADD CONSTRAINT imovel_fk FOREIGN KEY (id_imovel)
REFERENCES public.imovel (id_imovel) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: casa_uq | type: CONSTRAINT --
-- ALTER TABLE public.casa DROP CONSTRAINT IF EXISTS casa_uq CASCADE;
ALTER TABLE public.casa ADD CONSTRAINT casa_uq UNIQUE (id_imovel);
-- ddl-end --

-- object: endereco_fk | type: CONSTRAINT --
-- ALTER TABLE public.imovel DROP CONSTRAINT IF EXISTS endereco_fk CASCADE;
ALTER TABLE public.imovel ADD CONSTRAINT endereco_fk FOREIGN KEY (id_endereco)
REFERENCES public.endereco (id_endereco) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

--#####TEST INSERTS###
--ENDERECOS
INSERT INTO endereco
(numero, rua, cidade, bairro)
VALUES(10, 'Onde a vida Acaba', 'Ogod Mindé', 'Barreiro');

INSERT INTO endereco
(numero, rua, cidade, bairro)
VALUES(20, 'Vida de Ouro', 'Cidade dos anjos', 'Dark Kignight');

INSERT INTO endereco
(numero, rua, cidade, bairro)
VALUES(30, 'Shisui Guerreiro', 'Shippuden', 'Naruto');

--IMOVEIS
INSERT INTO imovel
(b_armario, n_quartos, n_suites, area, n_salas_estar, n_vagas_garagem, 
descricao, valor_aluguel, id_endereco)
VALUES(True, 1, 1, 1000.0, 2, 1, 'Um lugar belo.', 1111, 1);

INSERT INTO imovel
(b_armario, n_quartos, n_suites, area, n_salas_estar, n_vagas_garagem, 
descricao, valor_aluguel, id_endereco)
VALUES(False, 2, 2, 2000.0, 2, 2, 'Um lugar confortável.', 2222, 2);

INSERT INTO imovel
(b_armario, n_quartos, n_suites, area, n_salas_estar, n_vagas_garagem, 
descricao, valor_aluguel, id_endereco)
VALUES(True, 3, 3, 3000.0, 3, 3, 'Um lugar interessante.', 3333, 3);

--CASAS
INSERT INTO casa
(id_imovel)
VALUES(1);

INSERT INTO casa
(id_imovel)
VALUES(2);

INSERT INTO casa
(id_imovel)
VALUES(3);

--APARTAMENTO
INSERT INTO apartamento
(n_andar, valor_condominio, b_portaria, n_salas_jantar,
n_apartamento, id_imovel)
VALUES(1, 100, True, 1, 301, 1);

INSERT INTO apartamento
(n_andar, valor_condominio, b_portaria, n_salas_jantar,
n_apartamento, id_imovel)
VALUES(2, 200, True, 2, 201, 2);

INSERT INTO apartamento
(n_andar, valor_condominio, b_portaria, n_salas_jantar,
n_apartamento, id_imovel)
VALUES(3, 300, True, 3, 301, 3);