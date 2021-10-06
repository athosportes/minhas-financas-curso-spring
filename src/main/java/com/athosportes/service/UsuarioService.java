package com.athosportes.service;

import com.athosportes.model.entity.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {

    Usuario autenticar(String email, String senha);

    Usuario salvarUsuario (Usuario usuario);

    void validarEmail(String email);


}
