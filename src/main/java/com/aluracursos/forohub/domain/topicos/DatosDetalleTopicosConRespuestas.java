package com.aluracursos.forohub.domain.topicos;

import com.aluracursos.forohub.domain.respuestas.DatosDetalleRespuesta;

import java.util.List;

public record DatosDetalleTopicosConRespuestas(
        Long id,
        String titulo,
        String mensaje,
        List<DatosDetalleRespuesta> respuestas
) {
    public DatosDetalleTopicosConRespuestas(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getRespuestas().stream()
                        .map(DatosDetalleRespuesta::new)
                        .toList()
        );
    }
}