package com.lab.ver2.controller;

import com.lab.ver2.dto.*;
import com.lab.ver2.service.ProyectoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proyectos")
@RequiredArgsConstructor
public class ProyectoController {

    private final ProyectoService proyectoService;

    @GetMapping
    public ResponseEntity<List<ProyectoGetDTO>> getAllProyectos(){
        return ResponseEntity.ok(proyectoService.getAllProyectos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProyectoGetDTO> getProyectoById(@PathVariable Integer id){
        return ResponseEntity.ok(proyectoService.getProyectoById(id));
    }

    @PostMapping
    public ResponseEntity<ProyectoGetDTO> createProyecto(@RequestBody ProyectoPostDTO dto){
        return ResponseEntity.ok(proyectoService.createProyecto(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProyectoGetDTO> updateProyecto(@Valid @RequestBody ProyectoPostDTO dto, @PathVariable Integer id){
        return ResponseEntity.ok(proyectoService.updateProyecto(dto, id));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProyecto(@PathVariable Integer id){
        proyectoService.deleteProyecto(id);
        return ResponseEntity.noContent().build();
    }
    
}
