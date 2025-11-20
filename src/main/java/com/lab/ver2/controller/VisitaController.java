package com.lab.ver2.controller;

import com.lab.ver2.dto.*;
import com.lab.ver2.service.VisitaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/visitas")
@RequiredArgsConstructor
public class VisitaController {

    private final VisitaService visitaService;

    @GetMapping
    public ResponseEntity<List<VisitaGetDTO>> getAllVisitas(){
        return ResponseEntity.ok(visitaService.getAllVisitas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitaGetDTO> getVisitaById(@PathVariable Integer id){
        return ResponseEntity.ok(visitaService.getVisitaById(id));
    }

    @PostMapping
    public ResponseEntity<VisitaGetDTO> createVisita(@Valid @RequestBody VisitaPostDTO dto){
        return ResponseEntity.ok(visitaService.createVisita(dto));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<VisitaGetDTO> updateVisita(@Valid @RequestBody VisitaPostDTO dto, @PathVariable Integer id){
        return ResponseEntity.ok(visitaService.updateVisita(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisita(@PathVariable Integer id){
        visitaService.deleteVisita(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nocuenta-rfc")
    public List<VisitaGetDTO> getVisitaByRFC(@RequestParam String rfc){
        return visitaService.getVisitaByRFC(rfc);
    }
    
    
}
