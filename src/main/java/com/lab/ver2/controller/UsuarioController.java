package com.lab.ver2.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.lab.ver2.dto.*;
import com.lab.ver2.mapping.UsuarioMapper;
import com.lab.ver2.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class UsuarioController {
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


    @PostMapping(consumes = "application/json")
    public UsuarioGetDTO createUserJson(@Valid @RequestBody UsuarioPostDTO dto){
        UsuarioGetDTO dtoTmp = usuarioMapper.toDto(usuarioService.crearUsuario(dto));
        return dtoTmp;
    }

    @PostMapping
    public UsuarioGetDTO createUser(@Valid @ModelAttribute UsuarioPostDTO dto){
        UsuarioGetDTO dtoTmp = usuarioMapper.toDto(usuarioService.crearUsuario(dto));
        return dtoTmp;
    }

    @PutMapping("/{id}")
    public UsuarioGetDTO update(
        @Valid
        @PathVariable Integer id,
        @ModelAttribute UsuarioPostDTO dto){
        return usuarioService.actualizarUsuario(id, dto);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public UsuarioGetDTO updateJson(
        @Valid
        @PathVariable Integer id,
        @RequestBody UsuarioPostDTO dto){
        return usuarioService.actualizarUsuario(id, dto);
    }
    
    /*
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        usuarioService.eliminarUsuario(id);
    }
    */

}