package com.aluracursos.forohub.domain.usuarios;

import com.aluracursos.forohub.domain.perfiles.Perfiles;
import com.aluracursos.forohub.domain.perfiles.iPerfilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private iUsuarioRepository usuarioRepository;

    @Autowired
    private iPerfilesRepository perfilesRepository;

    public UsuarioService(PasswordEncoder passwordEncoder, iUsuarioRepository usuarioRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    public ResponseEntity guardarUsuario(DatosRegistroUsuario datos, UriComponentsBuilder uriComponentsBuilder) {
        String passwordEncriptada = passwordEncoder.encode(datos.contrasena());
        if (usuarioRepository.existsByCorreoElectronico(datos.correo_electronico())) {
            throw new IllegalArgumentException("El correo electrónico ya está registrado.");
        }

        Perfiles perfil = perfilesRepository.getReferenceById(datos.idPerfiles());


        Usuario usuario = new Usuario(
                datos.nombre(),
                datos.correo_electronico(),
                passwordEncriptada,
                perfil
        );

        usuarioRepository.save(usuario);
        var uri = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosDetalleUsuario(usuario));
    }

}
