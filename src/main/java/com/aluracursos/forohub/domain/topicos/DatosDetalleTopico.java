package com.aluracursos.forohub.domain.topicos;

import com.aluracursos.forohub.domain.cursos.Curso;
import com.aluracursos.forohub.domain.usuarios.Usuario;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        Estado status,
        LocalDateTime fechaCreacion,
        Long idUsuario,
        Long idCurso
) {
    public DatosDetalleTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getStatus(), topico.getFechaCreacion(), topico.getUsuario().getId(), topico.getCurso().getId());
    }
}
