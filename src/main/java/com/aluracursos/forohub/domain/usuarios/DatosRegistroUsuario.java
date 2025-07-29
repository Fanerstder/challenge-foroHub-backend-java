package com.aluracursos.forohub.domain.usuarios;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroUsuario(

        @NotBlank String nombre,
        @NotNull @Email String correo_electronico,
        @NotBlank  String contrasena,
        @NotNull Long idPerfiles
        ) {

}
