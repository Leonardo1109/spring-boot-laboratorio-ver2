package com.lab.ver2.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lab.ver2.model.TipoEquipo;
import com.lab.ver2.repository.TipoEquipoRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tipos-equipo")
@RequiredArgsConstructor
public class TipoEquipoController {
    
    private final TipoEquipoRepository tipoEquipoRepository;
    
    @GetMapping
    public ResponseEntity<List<TipoEquipo>> getAll(){
        return ResponseEntity.ok(tipoEquipoRepository.findAll());
    }
}
