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

    @PostMapping(consumes = "application/json")
    public ResponseEntity<ProyectoGetDTO> createProyectoJson(@Valid @RequestBody ProyectoPostDTO dto){
        return ResponseEntity.ok(proyectoService.createProyecto(dto));
    }

    @PostMapping()
    public ResponseEntity<ProyectoGetDTO> createProyectoForm(@Valid @ModelAttribute ProyectoPostDTO dto){
        return ResponseEntity.ok(proyectoService.createProyecto(dto));
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<ProyectoGetDTO> updateProyectoJson(@Valid @RequestBody ProyectoPostDTO dto, @PathVariable Integer id){
        return ResponseEntity.ok(proyectoService.updateProyecto(dto, id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProyectoGetDTO> updateProyectoForm(@Valid @ModelAttribute ProyectoPostDTO dto, @PathVariable Integer id){
        return ResponseEntity.ok(proyectoService.updateProyecto(dto, id));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProyecto(@PathVariable Integer id){
        proyectoService.deleteProyecto(id);
        return ResponseEntity.noContent().build();
    }
    
}
