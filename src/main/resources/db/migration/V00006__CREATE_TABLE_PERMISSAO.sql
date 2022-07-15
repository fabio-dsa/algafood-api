CREATE TABLE IF NOT EXISTS permissao (
    id serial NOT NULL,
    chave VARCHAR(255) NOT NULL,
    descricao VARCHAR(255) NOT NULL,

    CONSTRAINT pk_permissao_id PRIMARY KEY(id)
);