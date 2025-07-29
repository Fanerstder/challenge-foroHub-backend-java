package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.respuestas.DatosDetalleRespuesta;
import com.aluracursos.forohub.domain.respuestas.DatosRegistroRespuesta;
import com.aluracursos.forohub.domain.respuestas.Respuesta;
import com.aluracursos.forohub.domain.respuestas.iRespuestaRepository;
import com.aluracursos.forohub.domain.topicos.Topico;
import com.aluracursos.forohub.domain.topicos.iTopicoRepository;
import com.aluracursos.forohub.domain.usuarios.Usuario;
import com.aluracursos.forohub.domain.usuarios.iUsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Controlador REST encargado de registrar respuestas a tópicos dentro del sistema.
 * Valida la existencia del usuario y del tópico antes de persistir la respuesta.
 * Retorna el detalle de la respuesta creada.
 *
 * Todos los endpoints requieren autenticación Bearer (JWT).
 * Ruta base: /respuestas
 *
 * @author Faner Santander
 * @version 1.0
 */
@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestasController {

    @Autowired
    private iRespuestaRepository respuestaRepository;

    @Autowired
    private iUsuarioRepository usuarioRepository;

    @Autowired
    private iTopicoRepository topicoRepository;

    /**
     * Endpoint POST para registrar una nueva respuesta en un tópico.
     * Verifica que el tópico y el usuario existan antes de guardar la respuesta.
     * Retorna la respuesta creada en formato detallado.
     *
     * @param datos DTO con la información para registrar la respuesta
     * @return ResponseEntity con los datos detallados de la respuesta registrada
     */
    @Transactional
    @PostMapping
    public ResponseEntity<DatosDetalleRespuesta> registrar(@RequestBody DatosRegistroRespuesta datos) {
        Topico topico = topicoRepository.findById(datos.idTopico())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado"));

        Usuario autor = usuarioRepository.findById(datos.idUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID " + datos.idUsuario()));



        Respuesta respuesta = new Respuesta();
        respuesta.setMensaje(datos.mensaje());
        respuesta.setTopico(topico);
        respuesta.setAutor(autor);
        respuesta.setFechaCreacion(LocalDateTime.now());
        respuesta.setSolucion(datos.solucion());


        respuestaRepository.save(respuesta);
        DatosDetalleRespuesta detalle = new DatosDetalleRespuesta(respuesta);
        //return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(detalle);

    }
}
