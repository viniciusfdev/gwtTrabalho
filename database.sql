DROP TABLE IF EXISTS endereco;
DROP SEQUENCE IF EXISTS endereco_sequence;
CREATE SEQUENCE endereco_sequence;
CREATE TABLE endereco (
  idendereco int NOT NULL PRIMARY KEY DEFAULT nextval('endereco_sequence'),
  bairro varchar(100) NOT NULL
);

INSERT INTO endereco VALUES (1,'Barreiro'),(2,'Barro Preto');
