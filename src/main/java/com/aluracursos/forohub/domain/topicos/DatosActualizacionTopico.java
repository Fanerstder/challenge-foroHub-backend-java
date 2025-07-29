package com.aluracursos.forohub.domain.topicos;

import jakarta.validation.constraints.NotNull;

public record DatosActualizacionTopico(
        @NotNull Long id,
                 String titulo,
                 String mensaje
) {
}
