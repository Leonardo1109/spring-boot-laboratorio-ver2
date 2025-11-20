package com.lab.ver2.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lab.ver2.dto.RolDTO;
import com.lab.ver2.mapping.RolMapper;
import com.lab.ver2.repository.RolRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RolController {

    private final RolRepository rolRepository;
    private final RolMapper rolMapper;
    
    @GetMapping
    public ResponseEntity<List<RolDTO>> getRoles() {
        return ResponseEntity.ok(rolMapper.toDtos(rolRepository.findAll()));
    }

}
