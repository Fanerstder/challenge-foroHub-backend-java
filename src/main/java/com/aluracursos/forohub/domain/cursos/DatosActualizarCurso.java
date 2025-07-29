package com.aluracursos.forohub.domain.cursos;

import jakarta.validation.constraints.NotNull;

/**
 * DTO utilizado para actualizar la categoría de un curso existente.
 * Contiene el identificador del curso y la nueva categoría seleccionada.
 *
 * Usado por el controlador y el servicio para aplicar cambios sobre la entidad {@link Curso}.
 *
 * @param id ID del curso a actualizar (no nulo)
 * @param categoria nueva categoría que se asignará al curso
 *
 * @author Faner Santander
 * @version 1.0
 */
public record DatosActualizarCurso(
        @NotNull Long id,
        Categoria categoria
) {
}
