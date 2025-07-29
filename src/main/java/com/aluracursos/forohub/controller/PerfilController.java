package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.perfiles.DatosListaPerfiles;
import com.aluracursos.forohub.domain.perfiles.DatosPerfiles;
import com.aluracursos.forohub.domain.perfiles.Perfiles;
import com.aluracursos.forohub.domain.perfiles.iPerfilesRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST encargado de gestionar las operaciones relacionadas con perfiles.
 * Permite registrar nuevos perfiles y obtener un listado paginado de los existentes.
 *
 * Todos los endpoints requieren autenticación mediante esquema Bearer (JWT).
 * Ruta base: /perfiles
 *
 * @author Faner Santander
 * @version 1.0
 */
@RestController
@RequestMapping("/perfiles")
@SecurityRequirement(name = "bearer-key")
public class PerfilController {

    @Autowired
    private iPerfilesRepository repository;

    /**
     * Endpoint POST para registrar un nuevo perfil en el sistema.
     * Valida los datos de entrada y los persiste en la base de datos.
     *
     * @param datos DTO con la información del perfil a registrar
     */
    @Transactional
    @PostMapping
    public void registrarPerfil(@RequestBody @Valid DatosPerfiles datos) {
         repository.save(new Perfiles(datos));
    }

    /**
     * Endpoint GET para listar todos los perfiles disponibles de forma paginada.
     * Los perfiles se ordenan por nombre y se retornan en formato DTO.
     *
     * @param paginacion configuración de paginación (size, sort, page)
     * @return página con listado de perfiles
     */
    @GetMapping
    public Page<DatosListaPerfiles> listarPerfiles(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion) {
        return repository.findAll(paginacion).map(DatosListaPerfiles::new);
    }
}
