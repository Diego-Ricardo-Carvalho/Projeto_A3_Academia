CREATE DATABASE IF NOT EXISTS academia_javasfit;
USE academia_javasfit;
SHOW DATABASES;
SHOW TABLES;
DESC membros;
DROP TABLE membro_planos;
SELECT * FROM funcionarios;
SELECT * FROM treinos;
SELECT * FROM exercicios;
SELECT * FROM gerentes;

CREATE TABLE IF NOT EXISTS planos(
id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(17) NOT NULL,
valor DOUBLE NOT NULL);

CREATE TABLE IF NOT EXISTS membros(
id_plano INT NOT NULL,
id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(200) NOT NULL,
cpf VARCHAR(14) NOT NULL UNIQUE,
telefone VARCHAR(19) NOT NULL,
endereco VARCHAR(255) NOT NULL,
data_cadastro DATE,
FOREIGN KEY (id_plano) REFERENCES planos(id));

CREATE TABLE IF NOT EXISTS membro_planos(
id_memplan INT AUTO_INCREMENT PRIMARY KEY,
 id_membro INT NOT NULL,
 id_plano INT NOT NULL,
 FOREIGN KEY (id_membro) REFERENCES membros(id),
 FOREIGN KEY (id_plano) REFERENCES planos(id));

 CREATE TABLE IF NOT EXISTS pagamentos(
 id INT AUTO_INCREMENT PRIMARY KEY,
 id_memplan INT NOT NULL,
 status_pagamento BOOLEAN NOT NULL,
 valor DOUBLE NOT NULL,
 valor_pago DOUBLE NOT NULL, #vou pegar quando efetuar o pagamento
 percentual_desconto INT NOT NULL,	
 mes VARCHAR(9) NOT NULL,
 data_pagamento DATE NOT NULL, #vou pegar a data de hoje
 FOREIGN KEY(id_memplan) REFERENCES membro_plano(id_memplan));
  
  
  CREATE TABLE IF NOT EXISTS funcionarios(
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(200) NOT NULL,
  cpf VARCHAR(14) NOT NULL UNIQUE,
  login VARCHAR(50) NOT NULL UNIQUE,
  senha VARCHAR(50) NOT NULL,
  cargo VARCHAR(16) NOT NULL);
   
  
CREATE TABLE IF NOT EXISTS exercicios(
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
nome VARCHAR(60) NOT NULL UNIQUE,
maquina VARCHAR(60) NOT NULL,
grupo_muscular VARCHAR(60) NOT NULL);

CREATE TABLE IF NOT EXISTS treinos(
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
nome VARCHAR(60) NOT NULL UNIQUE);

CREATE TABLE IF NOT EXISTS exercicio_treinos(
id_treino INT NOT NULL,
id_exercicio INT NOT NULL,
repeticoes INT NOT NULL,
series INT NOT NULL,
peso_recomendado VARCHAR(4) NOT NULL,
dia_semana VARCHAR(8) NOT NULL,
PRIMARY KEY (id_treino, id_exercicio), 
FOREIGN KEY (id_treino) REFERENCES treinos(id) ON DELETE CASCADE,
FOREIGN KEY (id_exercicio) REFERENCES exercicios(id) ON DELETE CASCADE);
  
  CREATE TABLE IF NOT EXISTS membro_treino(
  id_memtre INT AUTO_INCREMENT PRIMARY KEY,
 id_membro INT NOT NULL,
 id_treino INT NOT NULL,
 FOREIGN KEY (id_membro) REFERENCES membros(id),
 FOREIGN KEY (id_treino) REFERENCES treinos(id));

CREATE TABLE IF NOT EXISTS planejamentos( -- essa será a tabela treino requisitada na a3
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
id_memtre INT NOT NULL,
tipo VARCHAR(20) NOT NULL,
descricao VARCHAR(255) NOT NULL,
data_inicio DATE NOT NULL,
nome_treino VARCHAR(60),
duracao VARCHAR(30) NOT NULL,
FOREIGN KEY (id_membro) REFERENCES membros(id)
 ON DELETE CASCADE
  ON UPDATE CASCADE,
FOREIGN KEY (id_treino) REFERENCES treinos(id)
  ON DELETE RESTRICT
  ON UPDATE CASCADE);
  
  CREATE TABLE IF NOT EXISTS historico_atividades(
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_membro INT NOT NULL,
  id_planejamentos INT NOT NULL,
  nome_treino VARCHAR(60) NOT NULL,
  data_inicio DATE NOT NULL,
  duracao_treino VARCHAR(30) NOT NULL);
  
  
  
  INSERT INTO funcionarios (nome, cpf, login, senha, cargo) 
  VALUES ('Benício Cauã dos Sants', '248.847.483-52', 'dossantos', 'admin', 'Gerente');
  
  INSERT INTO treinos (nome) VALUES ('Treino estoura frango');