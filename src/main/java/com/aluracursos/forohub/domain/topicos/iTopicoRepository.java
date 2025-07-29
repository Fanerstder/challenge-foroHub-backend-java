package com.aluracursos.forohub.domain.topicos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface iTopicoRepository extends JpaRepository<Topico, Long> {

    @Query("SELECT t FROM Topico t WHERE t.status = 'ACTIVO'")
    Page<Topico> findAllByEstadoActivo( Pageable paginacion);

    @Query("SELECT t FROM Topico t LEFT JOIN FETCH t.respuestas WHERE t.id = :id")
    Topico buscarConRespuestas(@Param("id") Long id);
}
