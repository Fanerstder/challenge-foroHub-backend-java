package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.perfiles.Perfiles;
import com.aluracursos.forohub.domain.perfiles.iPerfilesRepository;
import com.aluracursos.forohub.domain.usuarios.*;
import com.aluracursos.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Controlador REST encargado del registro de nuevos usuarios en el sistema.
 * Valida los datos, codifica la contraseña, asocia el perfil correspondiente
 * y guarda el usuario en la base de datos. Retorna la URI del usuario creado.
 *
 * Ruta base: /registrar_usuario
 *
 * @author Faner Santander
 * @version 1.0
 */
@RestController
@RequestMapping("/registrar_usuario")
public class RegitrarUsuarioController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private iUsuarioRepository usuarioRepository;

    @Autowired
    private iPerfilesRepository perfilesRepository;

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Constructor para inyectar dependencias críticas del controlador.
     *
     * @param passwordEncoder codificador de contraseñas
     * @param usuarioRepository repositorio para gestión de usuarios
     */
    public RegitrarUsuarioController(PasswordEncoder passwordEncoder, iUsuarioRepository usuarioRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Endpoint POST para registrar un nuevo usuario.
     * Verifica si el correo ya existe, codifica la contraseña,
     * asocia un perfil y guarda el usuario. Retorna la URI del nuevo usuario
     * o un mensaje de error si el correo ya está registrado.
     *
     * @param datos DTO con los datos necesarios para registrar el usuario
     * @param uriComponentsBuilder builder para construir la URI del recurso creado
     * @return ResponseEntity con los datos del usuario creado o error de validación
     */
    @Transactional
    @PostMapping
    public ResponseEntity registraUsuario(@RequestBody @Valid DatosRegistroUsuario datos, UriComponentsBuilder uriComponentsBuilder) {
        try {
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
        } catch (IllegalArgumentException e) {
            Map<String, String> errorBody = new HashMap<>();
            errorBody.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorBody);
        }
    }
}
