INSERT INTO public.estado (id, nome)
VALUES (1, 'Roraima'),
       (2, 'Amazonas'),
       (3, 'Maranhão'),
       (4, 'Ceará'),
       (5, 'Tocantins');

ALTER SEQUENCE public.estado_id_seq RESTART 6;
