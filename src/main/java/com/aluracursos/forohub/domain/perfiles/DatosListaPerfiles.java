package com.aluracursos.forohub.domain.perfiles;

public record DatosListaPerfiles(
        Long id,
        String nombre
) {
    public DatosListaPerfiles(Perfiles perfiles) {
        this(perfiles.getId(), perfiles.getNombre());
    }
}
