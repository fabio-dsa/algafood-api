ALTER TABLE restaurante ADD COLUMN end_cep VARCHAR(10);
ALTER TABLE restaurante ADD COLUMN end_logradouro VARCHAR(150);
ALTER TABLE restaurante ADD COLUMN end_numero VARCHAR(20);
ALTER TABLE restaurante ADD COLUMN end_complemento VARCHAR(150);
ALTER TABLE restaurante ADD COLUMN end_bairro VARCHAR(150);
ALTER TABLE restaurante ADD COLUMN end_cidade_id BIGINT;

ALTER TABLE restaurante
ADD CONSTRAINT fk_cidade_id FOREIGN KEY(end_cidade_id)
REFERENCES cidade(id);
