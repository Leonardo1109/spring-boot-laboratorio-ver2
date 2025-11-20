package com.lab.ver2.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lab.ver2.model.Estatus;
import com.lab.ver2.repository.EstatusRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/estatus")
@RequiredArgsConstructor
public class EstatusController {
    
    private final EstatusRepository estatusRepository;
    
    @GetMapping
    public ResponseEntity<List<Estatus>> getAll(){
        return ResponseEntity.ok(estatusRepository.findAll());
    }
}
