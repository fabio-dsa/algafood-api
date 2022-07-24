CREATE TABLE IF NOT EXISTS usuario_grupo (
    usuario_id BIGINT NOT NULL,
    grupo_id BIGINT NOT NULL,

    CONSTRAINT fk_usuario_id FOREIGN KEY(usuario_id) REFERENCES usuario(id),
    CONSTRAINT fk_grupo_id FOREIGN KEY(grupo_id) REFERENCES grupo(id)
);