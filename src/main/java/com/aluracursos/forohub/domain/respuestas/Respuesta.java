package com.aluracursos.forohub.domain.respuestas;

import com.aluracursos.forohub.domain.topicos.Topico;
import com.aluracursos.forohub.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    private LocalDateTime fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario autor;

    private String solucion;

  //  public Respuesta(Long id, String mensaje, Topico topico, LocalDateTime fechaCreacion, Usuario autor, String solucion) {
  //      this.id = id;
  //      this.mensaje = mensaje;
  //      this.topico = topico;
   //     this.fechaCreacion = fechaCreacion;
   //     this.autor = autor;
   //     this.solucion = solucion;
   // }
}
