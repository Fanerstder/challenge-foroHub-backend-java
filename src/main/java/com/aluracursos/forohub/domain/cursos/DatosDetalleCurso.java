package com.aluracursos.forohub.domain.cursos;

/**
 * DTO para mostrar los detalles de un curso en respuestas HTTP.
 * Contiene el nombre y la categoría del curso.
 *
 * Incluye un constructor auxiliar que permite crear el DTO directamente
 * desde una instancia de {@link Curso}.
 *
 * @param nombre nombre del curso
 * @param categoria categoría del curso
 *
 * @author Faner Santander
 * @version 1.0
 */
public record DatosDetalleCurso(String nombre, Categoria categoria) {

    /**
     * Constructor que transforma una entidad {@link Curso} en un DTO {@link DatosDetalleCurso}.
     *
     * @param curso instancia de la entidad Curso
     */
    public DatosDetalleCurso(Curso curso){
         this(curso.getNombre(), curso.getCategoria());
     }
}
