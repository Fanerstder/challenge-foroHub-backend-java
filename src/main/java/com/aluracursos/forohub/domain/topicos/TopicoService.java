package com.aluracursos.forohub.domain.topicos;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TopicoService {

    @Autowired
    private iTopicoRepository topicoRepository;

    public void crearTopico(Topico topico) {
        topicoRepository.save(topico);
    }
}