INSERT INTO restaurante(id, cep, complemento, nome) VALUES
(1L, '0000001', 'Complemento Endereço Restaurante Um', 'Restaurante Um'),
(2L, '0000002', 'Complemento Endereço Restaurante Dois', 'Restaurante Dois');

INSERT INTO cliente(id, cep, complemento, nome) VALUES
(1L, '0000001', 'Complemento Endereço Cliente Um', 'Cliente Um'),
(2L, '0000002', 'Complemento Endereço Cliente Dois', 'Cliente Dois');

INSERT INTO produto(id, disponivel, nome, valor_unitario, restaurante_id) VALUES
(1L, true, 'Produto Um', 5.0, 1L),
(2L, true, 'Produto Dois', 8.0, 2L),
(3L, true, 'Produto Três', 4.0, 1L);

INSERT INTO sacola(id, forma_pagamento, fechada, valor_total, cliente_id) VALUES
(1L, 0, false, 0.0, 1L);