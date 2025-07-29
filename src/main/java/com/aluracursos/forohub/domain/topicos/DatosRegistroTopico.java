package com.aluracursos.forohub.domain.topicos;

import com.aluracursos.forohub.domain.respuestas.Respuesta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record DatosRegistroTopico(
         @NotBlank(message = "Titulo es obligatorio.") String titulo,
         @NotBlank(message = "Mensaje es obligatorio.") String mensaje,
         @NotNull(message = "El estado debe comenzar en activo.") Estado status,
         @NotNull LocalDateTime fechaCreacion,
         @NotNull(message = "El ID del usuario es obligatorio.") Long idUsuario,
         @NotNull(message = "El ID del curso es obligatorio.") Long idCurso

) {

    public DatosRegistroTopico(Topico topico) {
        this(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getStatus(),
                topico.getFechaCreacion(),
                topico.getUsuario().getId(),
                topico.getCurso().getId()
        );
    }
}
