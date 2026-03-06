/*
package com.lab.ver2.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.lab.ver2.dto.*;
import com.lab.ver2.mapping.UsuarioMapper;
import com.lab.ver2.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin-usuarios")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminUsuarioController {
    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    @GetMapping
    public List<UsuarioGetDTO> getAll(){
        return usuarioService.obtenerTodos();
    }

    @GetMapping("/{id}") 
    public UsuarioGetDTO getById(@PathVariable Integer id){
        return usuarioService.getById(id);
    }


    @PostMapping
    public UsuarioGetDTO createUser(@RequestBody UsuarioPostDTO dto){
        UsuarioGetDTO dtoTmp = usuarioMapper.toDto(usuarioService.crearUsuario(dto));
        return dtoTmp;
    }

    @PutMapping("/{id}")
    public UsuarioGetDTO update(
        @PathVariable Integer id,
        @RequestBody UsuarioPostDTO dto){
        return usuarioService.actualizarUsuario(id, dto);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        usuarioService.eliminarUsuario(id);
    }

}
*/