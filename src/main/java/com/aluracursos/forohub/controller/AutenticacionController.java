package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.usuarios.DatosAutenticacion;
import com.aluracursos.forohub.domain.usuarios.DatosTokenJWT;
import com.aluracursos.forohub.domain.usuarios.Usuario;
import com.aluracursos.forohub.infra.security.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST encargado de gestionar el proceso de autenticación de usuarios.
 * Expone el endpoint de login y genera un token JWT válido al autenticarse correctamente.
 *
 * Requiere un esquema de seguridad tipo Bearer (JWT) configurado previamente.
 *
 * Ruta base: /login
 *
 * @author Faner Santander
 * @version 1.0
 */

@RestController
@RequestMapping("/login")
@SecurityRequirement(name = "bearer-key")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    /**
     * Endpoint POST que permite iniciar sesión mediante credenciales de usuario.
     * Valida las credenciales y, en caso de éxito, retorna un token JWT para futuras peticiones seguras.
     *
     * @param datosAutenticacion DTO con correo electrónico y contraseña del usuario
     * @return ResponseEntity con el token JWT generado
     */
    @Transactional
    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutenticacion datosAutenticacion) {
        var token = new UsernamePasswordAuthenticationToken(datosAutenticacion.correoElectronico(), datosAutenticacion.contrasena());
        var autentication = manager.authenticate(token);
        var tokenJWT = tokenService.generarToken((Usuario) autentication.getPrincipal());
        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }
}
