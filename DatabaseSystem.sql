-- criação das tabelas do sistema
CREATE TABLE IF NOT EXISTS Cliente(
	id_cliente SERIAL PRIMARY KEY,
	nome TEXT,
	email TEXT,
	senha TEXT
);

CREATE TABLE IF NOT EXISTS Lojista(
	id_lojista SERIAL PRIMARY KEY,
	nome TEXT,
	email TEXT,
	senha TEXT
);

CREATE TABLE IF NOT EXISTS Produto(
	id_produto SERIAL PRIMARY KEY,
	preco FLOAT,
	nome TEXT,
	descricao TEXT,
	estoque INTEGER
);

-- inserindo alguns cadastros previamente para cliente
INSERT INTO Cliente (nome, email, senha) VALUES
('João Pedro', 'jp2017@uol.com.br', '12345jaum'),
('Amara Silva', 'amarasil@bol.com.br', 'amara82'),
('Maria Pereira', 'mariape@terra.com.br', '145aektm');


-- inserindo alguns cadastros previamente para lojista
INSERT INTO Lojista (nome, email, senha) VALUES
('Taniro Rodrigues', 'tanirocr@gmail.com', '123456abc'),
('Lorena Silva', 'lore_sil@yahoo.com.br', '12uhuuu@');

-- observando localmente os dados para testar o funcionamento do sistema
SELECT *
FROM Cliente

SELECT * 
FROM Lojista

SELECT *
FROM Produto