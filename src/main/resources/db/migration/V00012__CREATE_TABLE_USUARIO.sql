CREATE TABLE IF NOT EXISTS usuario (
    id serial NOT NULL,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL,
    data_cadastro timestamp NOT NULL,

    CONSTRAINT pk_usuario_id PRIMARY KEY(id)
);