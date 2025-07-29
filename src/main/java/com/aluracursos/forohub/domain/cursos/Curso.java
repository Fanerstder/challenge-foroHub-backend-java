package com.aluracursos.forohub.domain.cursos;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Entidad JPA que representa un curso en el sistema.
 * Cada curso contiene un nombre y una categoría tecnológica que lo clasifica.
 * Se utiliza para asociar cursos a tópicos dentro del foro.
 *
 * Mapeada a la tabla "cursos" en la base de datos.
 *
 * @author Faner Santander
 * @version 1.0
 */
@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class  Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    /**
     * Constructor que permite crear un curso a partir del DTO DatosCurso.
     *
     * @param datosCurso DTO con los datos requeridos para instanciar un curso
     */
    public Curso(DatosCurso datosCurso) {
        this.id = null;
        this.nombre = datosCurso.nombre();
        this.categoria = datosCurso.categoria();
    }


    /**
     * Actualiza la categoría del curso si se proporciona una nueva en los datos.
     *
     * @param datos DTO con la categoría actualizada del curso
     */

    public void actualizarInformaciones(@Valid DatosActualizarCurso datos) {
        if (datos.categoria() != null) {
            this.categoria = datos.categoria();
        }
    }

}
