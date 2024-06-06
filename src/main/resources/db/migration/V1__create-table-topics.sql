CREATE TABLE topics (
  id BIGINT NOT NULL AUTO_INCREMENT,
  titulo VARCHAR(100) UNIQUE NOT NULL,
  mensagem TEXT NOT NULL,
  data_de_criacao DATE NOT NULL,
  estado_do_topico TINYINT NOT NULL,
  autor VARCHAR(150),
  curso VARCHAR(100),
  PRIMARY KEY (id)
);