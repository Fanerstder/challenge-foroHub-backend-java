package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.cursos.Curso;
import com.aluracursos.forohub.domain.cursos.iCursoRepository;
import com.aluracursos.forohub.domain.topicos.*;
import com.aluracursos.forohub.domain.usuarios.Usuario;
import com.aluracursos.forohub.domain.usuarios.iUsuarioRepository;
import com.aluracursos.forohub.domain.topicos.TopicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Controlador REST responsable de gestionar operaciones relacionadas con tópicos del foro.
 * Permite registrar, listar, actualizar, eliminar y visualizar tópicos junto con sus respuestas.
 *
 * Todos los endpoints requieren autenticación mediante esquema Bearer (JWT).
 * Ruta base: /topicos
 *
 * @author Faner Santander
 * @version 1.0
 */
@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicosController {

    @Autowired
    private PagedResourcesAssembler<DatosListaTopico> pagedResourcesAssembler;

    @Autowired
    private DatosListaTopicoModelAssembler datosListaTopicoModelAssembler;

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private iTopicoRepository topicoRepository;

    @Autowired
    private iUsuarioRepository usuarioRepository;

    @Autowired
    private iCursoRepository cursoRepository;

    /**
     * Endpoint POST para registrar un nuevo tópico en el foro.
     * El tópico queda asociado al usuario y curso correspondientes.
     *
     * @param datos DTO con los datos necesarios para registrar el tópico
     * @param uriComponentsBuilder builder para construir la URI del tópico creado
     * @return ResponseEntity con los datos del tópico registrado
     */
    @Transactional
    @PostMapping
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = usuarioRepository.getReferenceById(datos.idUsuario());
        Curso curso = cursoRepository.getReferenceById(datos.idCurso());
        Topico topico = new Topico(datos, usuario, curso);

        topicoService.crearTopico(topico);

        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
    }


    /**
     * Endpoint GET para listar tópicos activos del foro.
     * La lista está paginada y ordenada por fecha de creación descendente.
     *
     * @param paginacion configuración de paginación y ordenamiento
     * @return ResponseEntity con página de tópicos listados
     */
    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> listarTopico(@PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.DESC) Pageable paginacion) {
        var pagina = topicoRepository.findAllByEstadoActivo(paginacion).map(DatosListaTopico::new);
        return ResponseEntity.ok(pagina);
    }

    /**
     * Endpoint PUT para actualizar los datos de un tópico existente.
     *
     * @param datos DTO con los nuevos datos del tópico
     * @return ResponseEntity con el tópico actualizado
     */
    @Transactional
    @PutMapping
    public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionTopico datos) {
        var topico = topicoRepository.getReferenceById(datos.id());
        topico.actualizarInformaciones(datos);

        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    /**
     * Endpoint DELETE para marcar un tópico como resuelto.
     *
     * @param id ID del tópico a cerrar
     * @return ResponseEntity con confirmación o error
     */
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        try {
            var topico = topicoRepository.getReferenceById(id);
            topico.cerrarTopico();

            return ResponseEntity.ok().body("El topico a sido resuelto");

        } catch (IllegalArgumentException e) {
             Map<String, String> errorBody = new HashMap<>();
             errorBody.put("error", e.getMessage());
              return ResponseEntity.badRequest().body(errorBody);
        }
    }

    /**
     * Endpoint GET para obtener los detalles completos de un tópico por ID.
     *
     * @param id ID del tópico a detallar
     * @return ResponseEntity con los datos del tópico
     */
    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity detallarTopico(@PathVariable Long id) {
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    /**
     * Endpoint GET para obtener los detalles de un tópico junto con sus respuestas asociadas.
     *
     * @param id ID del tópico
     * @return ResponseEntity con detalles del tópico y sus respuestas
     */
    @Transactional
    @GetMapping("/respuestas/{id}")
    public ResponseEntity<DatosDetalleTopicosConRespuestas> verConRespuestas(@PathVariable Long id) {
        Topico topico = topicoRepository.buscarConRespuestas(id);

        if (topico == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado");
        }

        DatosDetalleTopicosConRespuestas detalle = new DatosDetalleTopicosConRespuestas(topico);
        return ResponseEntity.ok(detalle);
    }

}
