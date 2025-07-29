package com.aluracursos.forohub.domain.usuarios;

import jakarta.persistence.Column;

public record DatosAutenticacion(

        String correoElectronico,
        String contrasena
) {
    public DatosAutenticacion(Usuario usuario) {
        this(usuario.getCorreoElectronico(), usuario.getContrasena());
    }
}
