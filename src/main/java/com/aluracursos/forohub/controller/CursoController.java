package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.cursos.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST que gestiona operaciones relacionadas con los cursos.
 * Permite registrar, listar, actualizar, eliminar y obtener detalles de cursos mediante endpoints seguros.
 *
 * Requiere autenticación con esquema Bearer (JWT).
 * Ruta base: /cursos
 *
 * @author Faner Santander
 * @version 1.0
 */
@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private iCursoRepository repository;

    /**
     * Endpoint POST para registrar un nuevo curso.
     * El curso se guarda en la base de datos utilizando los datos enviados en el cuerpo del request.
     *
     * @param datos DTO con la información del curso a registrar
     */
    @Transactional
    @PostMapping
    public void registrarCurso(@RequestBody @Valid DatosCurso datos) {
        repository.save(new Curso(datos));
    }

    /**
     * Endpoint GET para listar los cursos disponibles en formato paginado.
     * Aplica ordenamiento por nombre y tamaño de página configurable.
     *
     * @param paginacion configuración de paginación (size, sort, page)
     * @return página con listado de cursos
     */
    @GetMapping
    public Page<DatosListaCurso> listarCursos(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion){
        return repository.findAll(paginacion).map(DatosListaCurso::new);
    }

    /**
     * Endpoint PUT para actualizar la información de un curso existente.
     * Utiliza el identificador para obtener el curso y actualiza sus campos.
     *
     * @param datos DTO con la información actualizada del curso
     */
    @Transactional
    @PutMapping
    public void actualizarCurso(@RequestBody @Valid DatosActualizarCurso datos) {
        var curso = repository.getReferenceById(datos.id());
        curso.actualizarInformaciones(datos);
    }

    /**
     * Endpoint DELETE para eliminar un curso mediante su ID.
     *
     * @param id identificador del curso a eliminar
     */
    @Transactional
    @DeleteMapping("/{id}")
    public void eliminarCurso(@PathVariable Long id) {
        repository.deleteById(id);
        //return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint GET para obtener los detalles completos de un curso por su ID.
     *
     * @param id identificador del curso
     * @return ResponseEntity con los datos detallados del curso
     */
    @GetMapping("/{id}")
    public ResponseEntity detallarCurso(@PathVariable Long id) {
        var curso = repository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalleCurso(curso));
    }
}
