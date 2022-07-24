CREATE TABLE IF NOT EXISTS produto (
    id serial NOT NULL,
    nome VARCHAR(150) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    preco NUMERIC(5,2) NOT NULL,
    ativo boolean NOT NULL DEFAULT TRUE,
    restaurante_id BIGINT NOT NULL,

    CONSTRAINT pk_produto_id PRIMARY KEY(id),
    CONSTRAINT fk_restaurante_id FOREIGN KEY(restaurante_id) REFERENCES restaurante(id)
);