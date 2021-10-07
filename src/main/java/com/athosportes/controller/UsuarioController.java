package com.athosportes.controller;

import com.athosportes.dto.UsuarioDTO;
import com.athosportes.exception.RegraNegocioException;
import com.athosportes.model.entity.Usuario;
import com.athosportes.model.repository.UsuarioRepository;
import com.athosportes.service.UsuarioService;
import com.athosportes.service.impl.UsuarioServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/minhas-financas")
public class UsuarioController {

    private UsuarioRepository repository;
    private UsuarioService service;

    @PostMapping
    private ResponseEntity salvarUsuario(@RequestBody UsuarioDTO dto) {

        Usuario usuario = Usuario.builder()
                .nome(dto.getNome())
                .senha(dto.getEmail())
                .senha(dto.getSenha())
                .build();
        try {
            Usuario usuarioSalvo = service.salvarUsuario(usuario);
            return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
        } catch (RegraNegocioException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}