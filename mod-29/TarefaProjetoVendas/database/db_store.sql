CREATE DATABASE db_store;

CREATE TABLE tb_cliente (
	id INT NOT NULL UNIQUE,
	codigo VARCHAR(16) NOT NULL,
	nome VARCHAR(32) NOT NULL,
	CONSTRAINT pk_id_cliente PRIMARY KEY (id)
);

CREATE TABLE tb_produto (
	id INT NOT NULL,
	codigo VARCHAR(16) NOT NULL,
	nome VARCHAR(32) NOT NULL,
	descricao VARCHAR(64),
	valor REAL NOT NULL,
	CONSTRAINT pk_id_produto PRIMARY KEY (id)
);

CREATE SEQUENCE sq_cliente
START 1
INCREMENT 1
OWNED BY tb_cliente.id;

CREATE SEQUENCE sq_produto
START 1
INCREMENT 1
OWNED BY tb_produto.id;

SELECT * FROM tb_produto;
SELECT * FROM tb_cliente;

DROP TABLE tb_produto;
DROP TABLE tb_cliente;
