package com.athosportes.model.entity.repository;

import com.athosportes.model.entity.Usuario;
import com.athosportes.model.repository.UsuarioRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioRepositoryTests {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void deveVerificarAExistenciaDeUmEmail() {
        //Cenário
        Usuario usuario = Usuario.builder().nome("usuario").email("usuario@email.com").build();
        entityManager.persist(usuario);

        //ação/execução
        boolean result = repository.existsByEmail("usuario@email.com");

        //Verificação
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComEmail() {
        //cenario


        //ação
        boolean result = repository.existsByEmail("usuario@email.com");

        //verificação
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void devePersistirUmUsuarioNaBaseDeDados() {
        Usuario usuario = repository.save(Usuario.builder().nome("usuario").email("usuario@email.com").senha("senha").build());
        Assertions.assertThat(usuario.getId()).isNotNull();
    }

    @Test
    public void deveBuscarUmUsuarioPorEmail() {
        //Cenário
        Usuario usuario = Usuario.builder().nome("usuario").email("usuario@email.com").senha("senha").build();
        entityManager.persist(usuario);

        //verificacao
        Optional<Usuario> byEmail = repository.findByEmail(usuario.getEmail());

        //ação
        Assertions.assertThat(byEmail.isPresent()).isTrue();
    }

    @Test
    public void deveRetornarVazioAoBuscarUsuarioPorEmailQuandoNaoExisteNaBase() {
        //verificacao
        Optional<Usuario> byEmail = repository.findByEmail("usuario@email.com");

        //ação
        Assertions.assertThat(byEmail.isPresent()).isFalse();
    }
}
