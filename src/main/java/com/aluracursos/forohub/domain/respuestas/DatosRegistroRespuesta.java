package com.aluracursos.forohub.domain.respuestas;

import com.aluracursos.forohub.domain.topicos.Topico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public record DatosRegistroRespuesta(
        @NotBlank String mensaje,
        @NotNull Long idTopico,
        @NotNull LocalDateTime fechaCreacion,
        @NotNull Long idUsuario,
        String solucion
) {}