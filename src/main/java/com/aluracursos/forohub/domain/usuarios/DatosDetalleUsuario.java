package com.aluracursos.forohub.domain.usuarios;

import com.aluracursos.forohub.domain.perfiles.Perfiles;

public record DatosDetalleUsuario(
        String nombre,
        String Correo_electronico,
        Perfiles perfiles
) {
 public DatosDetalleUsuario(Usuario usuario){
     this(usuario.getNombre(), usuario.getCorreoElectronico(), usuario.getPerfiles());
 }
}
