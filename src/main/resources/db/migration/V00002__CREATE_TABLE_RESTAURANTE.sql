CREATE TABLE IF NOT EXISTS restaurante (
    id serial NOT NULL,
    taxa_frete NUMERIC(5,2) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    aberto BOOLEAN DEFAULT FALSE,
    cozinha_id BIGINT NOT NULL,

    CONSTRAINT pk_restaurante_id PRIMARY KEY(id),
    CONSTRAINT fk_cozinha_id FOREIGN KEY(cozinha_id) REFERENCES cozinha(id)
);