package com.athosportes.service;

import com.athosportes.exception.ErroAutenticacao;
import com.athosportes.exception.RegraNegocioException;
import com.athosportes.model.entity.Usuario;
import com.athosportes.model.repository.UsuarioRepository;
import com.athosportes.service.impl.UsuarioServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

    @SpyBean
    UsuarioServiceImpl service;

    @MockBean
    UsuarioRepository repository;

//    @BeforeEach
//    public void setUp() {
//        service = Mockito.spy(UsuarioServiceImpl.class);
//        //service = new UsuarioServiceImpl(repository);
//    }

    @Test
    public void deveSalvarUmUsuario() {
        Mockito.doNothing().when(service).validarEmail(Mockito.anyString());
        Usuario usuario = Usuario.builder()
                .nome("nome")
                .email("email@email.com")
                .senha("senha")
                .build();

        Mockito.when(repository.save(Mockito.any(Usuario.class))).thenReturn(usuario);

        Assertions.assertAll(() -> service.salvarUsuario(new Usuario()));


    }
    @Test
    public void deveValidarEmail() {
        //cenário
        Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);

        //ação/execução
        Assertions.assertAll(() -> service.validarEmail("email@email.com"));
    }

    @Test
    public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
        Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);
        Assertions.assertThrows(RegraNegocioException.class, () -> service.validarEmail("email@email.com"));
    }

    @Test
    public void deveLancarErroQuandoNaoEncontrarUsuarioCadastradoComOEmailInformado() {
        Mockito.when((repository.findByEmail(Mockito.anyString()))).thenReturn(Optional.empty());
        Assertions.assertThrows(ErroAutenticacao.class, () -> service.autenticar("email@email.com", "senha"));
    }

    @Test
    public void deveLancarErroQuandoSenhaNaoBater() {
        String senha = "senha";
        Usuario usuario = Usuario.builder().email("email@email.com").senha("senha").build();
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn((Optional.of(usuario)));

        Assertions.assertThrows(ErroAutenticacao.class, () -> service.autenticar("email@email.com", "123"));
    }

//    @Test
//    public void deveSalvarUmUsuario() {
//        repository.save();
//        Usuario usuario = Usuario.builder().nome("nome").email("email@email.com").senha("senha").build();
//
//    }
}