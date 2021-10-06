package com.athosportes.controller;

import com.athosportes.model.entity.Usuario;
import com.athosportes.model.repository.UsuarioRepository;
import com.athosportes.service.impl.UsuarioServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/minhas-financas")
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioController {

    private UsuarioRepository repository;
    private UsuarioServiceImpl service;

    @PostMapping
    private Usuario salvarUsuario(@RequestBody Usuario usuario) {
        return service.salvarUsuario(usuario);
    }
}