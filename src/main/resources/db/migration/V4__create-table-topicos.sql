CREATE TABLE topicos (
   id BIGINT NOT NULL AUTO_INCREMENT,
   titulo VARCHAR(100) NOT NULL,
   mensaje VARCHAR(255) NOT NULL,
   fecha_creacion DATETIME NOT NULL,
   status VARCHAR(100) NOT NULL,
   usuario_id BIGINT NOT NULL,
   curso_id BIGINT NOT NULL,
   respuesta_destacada_id BIGINT,
   PRIMARY KEY (id),
   CONSTRAINT fk_topicos_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
   CONSTRAINT fk_topicos_curso_id FOREIGN KEY (curso_id) REFERENCES cursos(id)
) ENGINE=InnoDB;