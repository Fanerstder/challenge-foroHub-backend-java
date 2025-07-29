package com.aluracursos.forohub.domain.respuestas;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(
        Long id,
        String mensaje,
        String solucion,
        LocalDateTime fechaCreacion,
        Long idTopico
) {
    public DatosDetalleRespuesta(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getSolucion(),
                respuesta.getFechaCreacion(),
                respuesta.getTopico().getId()
        );
    }
}
