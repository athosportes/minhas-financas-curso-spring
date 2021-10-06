package com.athosportes.model.repository;

import com.athosportes.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //Possibilidades
    //Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String email);
}
