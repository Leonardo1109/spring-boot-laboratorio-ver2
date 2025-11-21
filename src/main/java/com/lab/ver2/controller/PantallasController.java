package com.lab.ver2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lab.ver2.service.EquipoService;

import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/pantallas")
@RequiredArgsConstructor
public class PantallasController {

    private final EquipoService equipoService;

    @GetMapping
    public String home() {
        return "index";  // tu página principal con sidebar
    }

    @GetMapping("/inicio")
    public String retornoHome() {
        return "inicio"; // el contenido que se carga en main-content
    }

    @GetMapping("/equipos/crear")
    public String crearEquipoForm() {
        return "equipos/crear-equipo";
    }

    @GetMapping("/equipos/editar")
    public String editarEquipo() {
        return "equipos/editar-equipo";
    }

    @GetMapping("/equipos/form-editar")
    public String getFormEditar(@RequestParam Integer id, Model model) {
        model.addAttribute("equipo", equipoService.getEquipoById(id));
        return "equipos/form-editar-fragment";
    }

    @GetMapping("/equipos/buscar")
    public String buscarEquipos(@RequestParam String search, Model model) {
        model.addAttribute("equipos", equipoService.searchByDescripcion(search));
        return "equipos/resultados-fragment";
    }


}
