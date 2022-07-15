CREATE TABLE IF NOT EXISTS cidade (
    id serial NOT NULL,
    nome VARCHAR(255) NOT NULL,
    estado_id BIGINT NOT NULL,

    CONSTRAINT pk_cidade_id PRIMARY KEY(id),
    CONSTRAINT fk_estado_id FOREIGN KEY(estado_id) REFERENCES estado(id)
);