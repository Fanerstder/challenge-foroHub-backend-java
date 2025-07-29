package com.aluracursos.forohub.domain.cursos;

/**
 * DTO para representar un curso dentro de una lista de resultados.
 * Contiene el ID, nombre y categoría del curso.
 *
 * Incluye un constructor auxiliar para transformar directamente una entidad {@link Curso}
 * en una instancia de {@link DatosListaCurso}.
 *
 * @param id identificador único del curso
 * @param nombre nombre del curso
 * @param categoria categoría asignada al curso
 *
 * @author Faner Santander
 * @version 1.0
 */
public record DatosListaCurso(
        Long id,
        String nombre,
        Categoria categoria
) {
    /**
     * Constructor que genera el DTO a partir de una entidad {@link Curso}.
     *
     * @param curso instancia de la entidad Curso
     */
    public DatosListaCurso(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}
