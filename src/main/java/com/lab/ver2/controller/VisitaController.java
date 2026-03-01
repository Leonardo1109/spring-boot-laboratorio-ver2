package com.lab.ver2.controller;

import com.lab.ver2.dto.*;
import com.lab.ver2.mapping.VisitaMapper;
import com.lab.ver2.model.Visita;
import com.lab.ver2.service.VisitaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/visitas")
@RequiredArgsConstructor
public class VisitaController {

    private final VisitaService visitaService;
    private final VisitaMapper visitaMapper;

    @GetMapping
    public ResponseEntity<List<VisitaGetDTO>> getAllVisitas(){
        return ResponseEntity.ok(visitaService.getAllVisitas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitaGetDTO> getVisitaById(@PathVariable Integer id){
        return ResponseEntity.ok(visitaService.getVisitaById(id));
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<VisitaGetDTO> createVisitaJson(@Valid @RequestBody VisitaPostDTO dto){
        return ResponseEntity.ok(visitaService.createVisita(dto));
    }

    @PostMapping
    public ResponseEntity<VisitaGetDTO> createVisitaForm(@Valid @ModelAttribute VisitaPostDTO dto){
        return ResponseEntity.ok(visitaService.createVisita(dto));
    }
    
    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<VisitaGetDTO> updateVisitaJson(@Valid @RequestBody VisitaPostDTO dto, @PathVariable Integer id){
        return ResponseEntity.ok(visitaService.updateVisita(dto, id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<VisitaGetDTO> updateVisitaForm(@Valid @ModelAttribute VisitaPostDTO dto, @PathVariable Integer id){
        return ResponseEntity.ok(visitaService.updateVisita(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisita(@PathVariable Integer id){
        visitaService.deleteVisita(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public Page<VisitaGetDTO> buscarVisitas(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 5, sort = "id") Pageable pageable
    ) {

        Page<Visita> pagina = visitaService.buscarVisita(search, pageable);

        return pagina.map(visitaMapper::toDto);
    }   
}