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
    public ResponseEntity<List<EquipoGetDTO>> getAllEquipos(@RequestParam(required = false) String search){
        List<EquipoGetDTO> equipos = equipoService.getAllEquipos();

        if (search != null && !search.isBlank()) {
            equipos = equipos.stream()
                    .filter(e -> e.getDescripcion().toLowerCase().contains(search.toLowerCase()))
                    .toList();
        }

        return ResponseEntity.ok(equipos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EquipoGetDTO> getEquipoById(@PathVariable Integer id){
        return ResponseEntity.ok(equipoService.getEquipoById(id));
    }
    
    @PostMapping(consumes = "application/json")
    public ResponseEntity<EquipoGetDTO> createEquipoJson(@Valid @RequestBody EquipoPostDTO dto){
        return ResponseEntity.ok(equipoService.createEquipo(dto));
    }

    @PostMapping
    public ResponseEntity<EquipoGetDTO> createEquipoForm(@Valid @ModelAttribute EquipoPostDTO dto){
        return ResponseEntity.ok(equipoService.createEquipo(dto));
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<EquipoGetDTO> updateEquipo(@Valid @RequestBody EquipoPostDTO dto, @PathVariable Integer id){
        return ResponseEntity.ok(equipoService.updateEquipo(dto, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipoGetDTO> updateEquipoForm(@Valid @ModelAttribute EquipoPostDTO dto, @PathVariable Integer id){
        return ResponseEntity.ok(equipoService.updateEquipo(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipo(@PathVariable Integer id){
        equipoService.deleteEquipo(id);
        return ResponseEntity.noContent().build();
    }

    // cambiar estado
    @PatchMapping("/estatus")
    public ResponseEntity<EquipoGetDTO> cambiarEstatus(
            @RequestParam Integer equipoId,
            @RequestParam Integer estatusId) {

        equipoService.cambiarEstatus(equipoId, estatusId);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/asistencia/{idEquipo}")
    public ResponseEntity<Void> editarAsistenciaPorEquipo(
        @Valid @ModelAttribute AsistenciaPostDTO dtoAsistencia, 
        @PathVariable Integer idEquipo,
        @RequestParam Integer idEstatus) {
        
        equipoService.editarAsistenciaPorEquipo(dtoAsistencia, idEquipo, idEstatus);
        return ResponseEntity.noContent().build();
    }
}
