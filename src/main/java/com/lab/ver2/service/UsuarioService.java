package com.lab.ver2.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lab.ver2.dto.*;
import com.lab.ver2.mapping.UsuarioMapper;
import com.lab.ver2.model.Usuario;
import com.lab.ver2.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;

    public Usuario crearUsuario(UsuarioPostDTO dto){
        Usuario usuario = usuarioMapper.toUsuario(dto);
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setActivo(true);
        return usuarioRepository.save(usuario);
    }

    public List<UsuarioGetDTO> obtenerTodos() {
        return usuarioMapper.toDtos(usuarioRepository.findAll());
    }

    public UsuarioGetDTO getById(Integer id){
        return usuarioMapper.toDto(usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));
    }

    public void eliminarUsuario(Integer id) {
        
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        if (usuario.getUserName().equals(username)) {
            throw new RuntimeException("No puedes deshabilitar tu propio usuario");
        }

        usuario.setActivo(false);
        
        usuarioRepository.save(usuario);
    }
    
    public UsuarioGetDTO actualizarUsuario(Integer id, UsuarioPostDTO dto) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    
        usuario.setUserName(dto.getUserName());
        usuario.setNombre(dto.getNombre());
        usuario.setEsAdmin(dto.isEsAdmin());
    
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
    
        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }
    
}
