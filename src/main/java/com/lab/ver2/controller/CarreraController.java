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

    @GetMapping("/options")
    public String getCarreraOptions() {
        return carreraRepository.findAll().stream()
                .map(c -> "<option value=\"" + c.getId() + "\">" + c.getNombre() + "</option>")
                .reduce("", String::concat);
    }

    @GetMapping("/options-checkbox")
    public String getCarreraCheckboxes() {
        return 
            "<div class='d-flex flex-column overflow-hidden'>" + 
            carreraRepository.findAll().stream()
                .map(c -> 
                    "<label class='d-flex align-items-center mb-2 p-2 border rounded shadow-sm w-auto'>" +
                        "<input type='checkbox' class='form-check-input me-3' name='carrerasIds' value='" + c.getId() + "'>" +
                        "<span class='text-truncate'>" + c.getNombre() + "</span>" +
                    "</label>"
                )
                .reduce("", String::concat)
            + "</div>";
    }


}

