package com.aluracursos.forohub.domain.usuarios;

import com.aluracursos.forohub.domain.topicos.DatosRegistroTopico;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface iUsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByCorreoElectronico(String username);

    boolean existsByCorreoElectronico(@NotNull @Email String correo_electronico);
    //Usuario usuario = iUsuarioRepository.findById(DatosRegistroTopico.idUsuario()).orElseThrow();

}
