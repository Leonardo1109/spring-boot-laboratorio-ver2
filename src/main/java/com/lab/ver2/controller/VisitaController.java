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
    public String buscarResponsables(@RequestParam("texto") String texto) {

        if (texto == null || texto.isBlank()) {
            return "<div class='text-muted'>Escribe un RFC o Número de Cuenta...</div>";
        }

        var resultados = visitaService.searchByNCRFC(texto);

        if (resultados.isEmpty()) {
            return "<div class='text-danger'>No se encontraron resultados.</div>";
        }

        StringBuilder html = new StringBuilder("<div>");

        for (var v : resultados) {
            html.append(
                "<label class='d-flex align-items-center mb-2 p-2 border rounded shadow-sm w-100'>" +
                    "<input type='checkbox' class='form-check-input me-3' " +
                        "name='visitasIds' value='" + v.getId() + "'>" +
                    "<span class='text-truncate'>" + 
                        v.getNoCuentaRFC() + " — " + v.getNombre() +
                    "</span>" +
                "</label>"
            );            
        }

        html.append("</div>");
        return html.toString();
    }



}
