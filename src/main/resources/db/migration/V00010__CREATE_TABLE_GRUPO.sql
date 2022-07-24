CREATE TABLE IF NOT EXISTS grupo (
    id serial NOT NULL,
    nome VARCHAR(150) NOT NULL,

    CONSTRAINT pk_grupo_id PRIMARY KEY(id)
);