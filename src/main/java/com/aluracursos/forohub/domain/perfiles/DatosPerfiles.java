package com.aluracursos.forohub.domain.perfiles;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosPerfiles(

        @NotBlank String nombre
) {
    public DatosPerfiles(Perfiles perfiles) {
        this(perfiles.getNombre());
    }
}
