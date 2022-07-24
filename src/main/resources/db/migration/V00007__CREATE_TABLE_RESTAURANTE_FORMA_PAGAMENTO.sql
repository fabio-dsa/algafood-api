CREATE TABLE IF NOT EXISTS restaurante_forma_pagamento (
    restaurante_id BIGINT NOT NULL,
    forma_pagamento_id BIGINT NOT NULL,

    CONSTRAINT fk_restaurante_id FOREIGN KEY(restaurante_id) REFERENCES restaurante(id),
    CONSTRAINT fk_forma_pagamento_id FOREIGN KEY(forma_pagamento_id) REFERENCES forma_pagamento(id)
);