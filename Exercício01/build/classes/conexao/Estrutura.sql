DROP DATABASE IF EXISTS banco_de_dados_exercicio_01;
CREATE DATABASE IF NOT EXISTS banco_de_dados_exercicio_01;

USE banco_de_dados_exercicio_01;

CREATE TABLE alunos(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(200),
    codigo_matricula VARCHAR(11),
    nota_1 FLOAT,
    nota_2 FLOAT,
    nota_3 FLOAT,
    media FLOAT,
    frequencia TINYINT
);
