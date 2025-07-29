package com.aluracursos.forohub.domain.topicos;

import com.aluracursos.forohub.domain.cursos.Categoria;
import com.aluracursos.forohub.domain.cursos.Curso;
import com.aluracursos.forohub.domain.perfiles.DatosPerfiles;
import com.aluracursos.forohub.domain.perfiles.Perfiles;
import com.aluracursos.forohub.domain.usuarios.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;



@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureJsonTesters
class iTopicoRepositoryTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DatosRegistroTopico> datosRegistroTopicoJson;

    @MockBean
    private iTopicoRepository topicoRepository;

    @Test
    @DisplayName("Deberia devolver código http 400 cuando las informaciones son invalidas")
    @WithMockUser
    void registar_escenario1 () throws Exception {
        var response = mvc
                .perform(post("/topicos"))
                .andReturn().getResponse();
        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Debería devolver código http 200 cuando las informaciones son válidas")
    @WithMockUser
    void registrar_escenario2() throws Exception {

        DatosPerfiles datosPerfil = new DatosPerfiles("INVITADO");
        Perfiles perfil = new Perfiles(datosPerfil);
        var autor = new Usuario("juan perez", "juanp@example.com", "123456", perfil);
        var curso = new Curso(2L, "Spring Boot", Categoria.JAVA);

        DatosRegistroTopico datos = new DatosRegistroTopico(
                "Titulo",
                "Mensaje",
                Estado.ACTIVO,
                LocalDateTime.now(),
                2L,
                2L
        );

        Topico topico = new Topico(datos, autor, curso);
        when(topicoRepository.save(any())).thenReturn(topico);

        var response = mvc
                .perform(post("/topicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(datosRegistroTopicoJson.write(datos).getJson()))
                .andReturn().getResponse();

        assertEquals(201, response.getStatus());
    }

}