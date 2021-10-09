package com.athosportes.controller;

import com.athosportes.dto.UsuarioDTO;
import com.athosportes.exception.ErroAutenticacao;
import com.athosportes.exception.RegraNegocioException;
import com.athosportes.model.entity.Usuario;
import com.athosportes.model.repository.UsuarioRepository;
import com.athosportes.service.UsuarioService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/minhas-financas/usuario")
public class UsuarioController {

    private final UsuarioRepository repository;
    private final UsuarioService service;

    @PostMapping
    public ResponseEntity salvarUsuario(@RequestBody UsuarioDTO dto) {

        Usuario usuario = Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .build();
        try {
            Usuario usuarioSalvo = service.salvarUsuario(usuario);
            return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
        } catch (RegraNegocioException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/autenticar")
    public ResponseEntity autenticar(@RequestBody UsuarioDTO dto) {
        try {
            Usuario autenticar = service.autenticar(dto.getEmail(), dto.getSenha());
            return ResponseEntity.ok(autenticar);
        } catch (ErroAutenticacao ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }
}