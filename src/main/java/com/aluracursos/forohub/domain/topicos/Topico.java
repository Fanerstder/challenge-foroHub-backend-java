package com.aluracursos.forohub.domain.topicos;

import com.aluracursos.forohub.domain.cursos.Curso;
import com.aluracursos.forohub.domain.respuestas.Respuesta;
import com.aluracursos.forohub.domain.usuarios.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private Estado status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "topico", fetch = FetchType.LAZY)
    private List<Respuesta> respuestas;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "respuesta_destacada_id")
    private Respuesta respuestaDestacada;

    public Topico(DatosRegistroTopico datos, Usuario usuario, Curso curso) {
        this.id = null; // Se ignora porque es generado automáticamente
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaCreacion = datos.fechaCreacion(); // Ya viene desde el DTO
        this.status = datos.status();
        this.usuario = usuario; // Entidad obtenida por idUsuario
        this.curso = curso;     // Entidad obtenida por idCurso
    }



//    public Topico(String titulo, String mensaje, Usuario usuario, Curso curso, List<Respuesta> respuestas, Respuesta respuestaDestacada) {
//        this.titulo = titulo;
//        this.mensaje = mensaje;
//        this.fechaCreacion = LocalDateTime.now();
//        this.status = Estado.ACTIVO;
//        this.usuario = usuario;
//        this.curso = curso;
//        this.respuestas = respuestas;
//        this.respuestaDestacada = respuestaDestacada;
//    }

    public void actualizarInformaciones(@Valid DatosActualizacionTopico datos) {

        if (datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
        if (datos.mensaje() != null){
            this.mensaje = datos.mensaje();
        }
    }

    public void cerrarTopico(){
        this.status = Estado.RESUELTO;
    }
}
