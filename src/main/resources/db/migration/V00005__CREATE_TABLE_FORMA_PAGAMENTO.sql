CREATE TABLE IF NOT EXISTS forma_pagamento (
    id serial NOT NULL,
    descricao VARCHAR(255) NOT NULL,

    CONSTRAINT pk_forma_pagamento_id PRIMARY KEY(id)
);