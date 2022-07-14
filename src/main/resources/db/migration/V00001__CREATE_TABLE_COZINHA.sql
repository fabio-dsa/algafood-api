CREATE TABLE IF NOT EXISTS cozinha (
    id serial NOT NULL,
    nome VARCHAR(255) NOT NULL,

    CONSTRAINT pk_cozinha_id PRIMARY KEY(id)
);