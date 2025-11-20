package com.lab.ver2.controller;

import com.lab.ver2.dto.*;
import com.lab.ver2.service.AsistenciaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/asistencias")
@RequiredArgsConstructor
public class AsistenciaController {

    private final AsistenciaService asistenciaService;

    @GetMapping
    public ResponseEntity<List<AsistenciaGetDTO>> getAllAsistencias(){
        return ResponseEntity.ok(asistenciaService.getAllAsistencias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsistenciaGetDTO> getAsistenciaById(@PathVariable Integer id){
        return ResponseEntity.ok(asistenciaService.getAsistenciaById(id));
    }

    @PostMapping
    public ResponseEntity<AsistenciaGetDTO> createAsistencia(@Valid @RequestBody AsistenciaPostDTO dto){
        return ResponseEntity.ok(asistenciaService.createAsistencia(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsistenciaGetDTO> updateAsistencia(@Valid @RequestBody AsistenciaPostDTO dto, @PathVariable Integer id){
        return ResponseEntity.ok(asistenciaService.updateAsistencia(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsistencia(@PathVariable Integer id){
        asistenciaService.deleteAsistencia(id);
        return ResponseEntity.noContent().build();
    }
    
}
