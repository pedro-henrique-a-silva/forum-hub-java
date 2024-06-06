CREATE TABLE courses (
  id bigint not null auto_increment,
  nome varchar(200) not null,
  categoria varchar(100) not null,

  primary key (id)
);

CREATE TABLE users(
  id bigint not null auto_increment,
  nome varchar(200) not null,
  email varchar(200) not null,
  senha varchar(200) not null,

  primary key (id)
);

CREATE TABLE answers(
  id bigint not null auto_increment,
  mensagem TEXT NOT NULL,
  topico bigint NOT NULL,
  data_criacao DATE NOT NULL DEFAULT(CURRENT_DATE),
  autor bigint not null,
  solucao TEXT not null,

  primary key (id),
  foreign key (topico) references topics(id),
  foreign key (autor) references users(id)
);

ALTER TABLE topics MODIFY autor bigint;
ALTER TABLE topics MODIFY curso bigint;

ALTER TABLE topics ADD FOREIGN KEY (autor) REFERENCES users(id);
ALTER TABLE topics ADD FOREIGN KEY (curso) REFERENCES courses(id);