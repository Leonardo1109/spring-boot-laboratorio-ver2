package com.lab.ver2.controller;

import com.lab.ver2.dto.*;
import com.lab.ver2.service.EquipoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/equipos")
@RequiredArgsConstructor
public class EquipoController {

    private final EquipoService equipoService;

    @GetMapping
    public ResponseEntity<List<EquipoGetDTO>> getAllEquipos(){
        return ResponseEntity.ok(equipoService.getAllEquipos());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EquipoGetDTO> getEquipoById(@PathVariable Integer id){
        return ResponseEntity.ok(equipoService.getEquipoById(id));
    }
    
    @PostMapping
    public ResponseEntity<EquipoGetDTO> createEquipo(@Valid @RequestBody EquipoPostDTO dto){
        return ResponseEntity.ok(equipoService.createEquipo(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipoGetDTO> updateEquipo(@Valid @RequestBody EquipoPostDTO dto, @PathVariable Integer id){
        return ResponseEntity.ok(equipoService.updateEquipo(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipo(@PathVariable Integer id){
        equipoService.deleteEquipo(id);
        return ResponseEntity.noContent().build();
    }
    
}
