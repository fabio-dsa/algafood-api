CREATE TABLE IF NOT EXISTS grupo_permissao (
    grupo_id BIGINT NOT NULL,
    permissao_id BIGINT NOT NULL,

    CONSTRAINT fk_grupo_id FOREIGN KEY(grupo_id) REFERENCES grupo(id),
    CONSTRAINT fk_permissao_id FOREIGN KEY(permissao_id) REFERENCES permissao(id)
);