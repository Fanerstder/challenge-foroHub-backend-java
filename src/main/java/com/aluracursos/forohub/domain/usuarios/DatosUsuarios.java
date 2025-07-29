package com.aluracursos.forohub.domain.usuarios;

public record DatosUsuarios(
        String nombre,
        String email,
        String contrasena,
        String perfiles
) {
}
