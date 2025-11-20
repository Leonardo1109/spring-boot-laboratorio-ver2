package com.lab.ver2.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lab.ver2.dto.CarreraDTO;
import com.lab.ver2.mapping.CarreraMapper;
import com.lab.ver2.repository.CarreraRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/carreras")
@RequiredArgsConstructor
public class CarreraController {
    private final CarreraRepository carreraRepository;
    private final CarreraMapper carreraMapper;
    
    @GetMapping
    public ResponseEntity<List<CarreraDTO>> getAll(){
        return ResponseEntity.ok(carreraMapper.toDtos(carreraRepository.findAll()));
    }
}

