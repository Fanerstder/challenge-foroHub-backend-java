ALTER TABLE topicos
ADD CONSTRAINT fk_topicos_respuesta_destacada
FOREIGN KEY (respuesta_destacada_id) REFERENCES respuestas(id);