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
	id_imovel serial NOT NULL,
	descricao varchar,
	id_endereco integer,
	CONSTRAINT pk_imovel PRIMARY KEY (id_imovel)

);
-- ddl-end --
ALTER TABLE public.imovel OWNER TO postgres;
-- ddl-end --

-- object: public.apartamento | type: TABLE --
-- DROP TABLE IF EXISTS public.apartamento CASCADE;
CREATE TABLE public.apartamento(
	id_apartamento serial NOT NULL,
	n_andar smallint,
	valor_condominio double precision DEFAULT 0,
	b_portaria boolean DEFAULT FALSE,
	n_salas_jantar smallint,
	id_imovel integer NOT NULL,
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
	id_endereco serial NOT NULL,
	numero smallint,
	rua varchar,
	cidade varchar,
	bairro varchar,
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

--INCREMENTS

INSERT INTO endereco (numero, rua, cidade, bairro) 
	VALUES(666, 'São João Del Rey', 'Bailão de barro', 'Lurdes');

INSERT INTO endereco (numero, rua, cidade, bairro) 
	VALUES(111, 'Cara Leojao', 'Timeto a Faca', 'Samanbaia');

INSERT INTO endereco (numero, rua, cidade, bairro) 
	VALUES(222, 'Ponta Esquerda do Parana', 'Belo Horizonte', 'Botijão');

INSERT INTO endereco (numero, rua, cidade, bairro) 
	VALUES(444, 'Maite Proença', 'Fernao Dias', 'Samuca');

INSERT INTO endereco (numero, rua, cidade, bairro) 
	VALUES(323, 'Magos de Mentira', 'Belas Artes', 'Paodelupe');

INSERT INTO endereco (numero, rua, cidade, bairro) 
	VALUES(66, 'Del Rey', 'Bailão de barro', 'Lurdes');

INSERT INTO endereco (numero, rua, cidade, bairro) 
	VALUES(55, 'Natamanu', 'Faca', 'Pedreira');

INSERT INTO endereco (numero, rua, cidade, bairro) 
	VALUES(222, 'Torce Dordogalo', 'Belo Horizonte', 'Casca Noz');

INSERT INTO endereco (numero, rua, cidade, bairro) 
	VALUES(33, 'Proença', 'Fernao Dias', 'Zillaine');

INSERT INTO endereco (numero, rua, cidade, bairro) 
	VALUES(77, 'Flask', 'Belas Artes', 'God das Artes');

INSERT INTO imovel (b_armario, n_quartos, n_suites, area, n_salas_estar, n_vagas_garagem, descricao, id_endereco)
	VALUES(true, 5, 1, 988.9, 1, 3, 'Proximo a escola e comercio.', 1);

INSERT INTO imovel (b_armario, n_quartos, n_suites, area, n_salas_estar, n_vagas_garagem, descricao, id_endereco)
	VALUES(true, 1, 2, 188.9, 2, 3, 'Proximo a escola e comercio.', 2);

INSERT INTO imovel (b_armario, n_quartos, n_suites, area, n_salas_estar, n_vagas_garagem, descricao, id_endereco)
	VALUES(false, 2, 0, 288.9, 2, 3, 'Proximo a escola e comercio.', 3);

INSERT INTO imovel (b_armario, n_quartos, n_suites, area, n_salas_estar, n_vagas_garagem, descricao, id_endereco)
	VALUES(true, 3, 0, 388.9, 3, 3, 'Proximo a escola e comercio.', 4);

INSERT INTO imovel (b_armario, n_quartos, n_suites, area, n_salas_estar, n_vagas_garagem, descricao, id_endereco)
	VALUES(false, 4, 2, 488.9, 2, 3, 'Proximo a escola e comercio.', 5);

INSERT INTO imovel (b_armario, n_quartos, n_suites, area, n_salas_estar, n_vagas_garagem, descricao, id_endereco)
	VALUES(true, 5, 3, 588.9, 2, 3, 'Proximo a escola e comercio.', 6);

INSERT INTO imovel (b_armario, n_quartos, n_suites, area, n_salas_estar, n_vagas_garagem, descricao, id_endereco)
	VALUES(false, 1, 1, 688.9, 2, 2, 'Proximo a escola e comercio.', 7);

INSERT INTO imovel (b_armario, n_quartos, n_suites, area, n_salas_estar, n_vagas_garagem, descricao, id_endereco)
	VALUES(true, 2, 0, 788.9, 1, 2, 'Proximo a escola e comercio.', 8);

INSERT INTO imovel (b_armario, n_quartos, n_suites, area, n_salas_estar, n_vagas_garagem, descricao, id_endereco)
	VALUES(true, 3, 1, 888.9, 3, 1, 'Proximo a escola e comercio.', 9);

INSERT INTO imovel (b_armario, n_quartos, n_suites, area, n_salas_estar, n_vagas_garagem, descricao, id_endereco)
	VALUES(true, 4, 0, 88.9, 4, 1, 'Proximo a escola e comercio.', 10);

INSERT INTO apartamento (n_andar, valor_condominio, b_portaria, n_salas_jantar, id_imovel)
	VALUES(1, 658000.5, true, 1, 1);

INSERT INTO apartamento (n_andar, valor_condominio, b_portaria, n_salas_jantar, id_imovel)
	VALUES(5, 208000.5, true, 3, 2);

INSERT INTO apartamento (n_andar, valor_condominio, b_portaria, n_salas_jantar, id_imovel)
	VALUES(3, 58000.1, false, 1, 3);

INSERT INTO apartamento (n_andar, valor_condominio, b_portaria, n_salas_jantar, id_imovel)
	VALUES(4, 158000.2, true, 2, 4);

INSERT INTO apartamento (n_andar, valor_condominio, b_portaria, n_salas_jantar, id_imovel)
	VALUES(1, 68000.5, false, 1, 5);

INSERT INTO casa (id_imovel) VALUES(6);

INSERT INTO casa (id_imovel) VALUES(7);

INSERT INTO casa (id_imovel) VALUES(8);

INSERT INTO casa (id_imovel) VALUES(9);

INSERT INTO casa (id_imovel) VALUES(10);
