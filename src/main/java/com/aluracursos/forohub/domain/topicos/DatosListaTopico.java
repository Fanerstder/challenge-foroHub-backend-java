package com.aluracursos.forohub.domain.topicos;

import java.time.LocalDateTime;

public record DatosListaTopico(
        Long id,
        String titulo,
        String mensaje,
        String nombreCurso,
        LocalDateTime fechaCreacion,
        Estado status
) {
    public DatosListaTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getCurso().getNombre(),
                topico.getFechaCreacion(),
                topico.getStatus()
        );
    }

}
