CREATE TABLE IF NOT EXISTS estado (
    id serial NOT NULL,
    nome VARCHAR(255) NOT NULL,

    CONSTRAINT pk_estado_id PRIMARY KEY(id)
);