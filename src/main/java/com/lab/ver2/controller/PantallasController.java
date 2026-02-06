package com.lab.ver2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lab.ver2.dto.EquipoGetDTO;
import com.lab.ver2.service.EquipoService;
import com.lab.ver2.service.ProyectoService;
import com.lab.ver2.service.VisitaService;

import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/pantallas")
@RequiredArgsConstructor
public class PantallasController {

    private final EquipoService equipoService;
    private final VisitaService visitaService;
    private final ProyectoService proyectoService;

    @GetMapping
    public String home() {
        return "index";  // tu página principal con sidebar
    }

    @GetMapping("/inicio")
    public String retornoHome() {
        return "inicio"; 
    }

    @GetMapping("/equipos/crear") // ruta
    public String crearEquipoForm() {
        return "equipos/crear-equipo"; // HTML
    }

    @GetMapping("/equipos/editar")
    public String editarEquipo(Model model) {
        model.addAttribute("equiposRecientes", equipoService.getFirst5());
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

    @GetMapping("/visitas/crear") // ruta
    public String crearVisitaForm() {
        return "visitas/crear-visita"; // HTML
    }

    @GetMapping("/visitas/editar")
    public String editarVisita(Model model) {
        model.addAttribute("visitasRecientes", visitaService.getFirst5());
        return "visitas/editar-visita";
    }

    @GetMapping("/visitas/form-editar")
    public String getFormEditarVisitas(@RequestParam Integer id, Model model) {
        model.addAttribute("visita", visitaService.getVisitaById(id));
        return "visitas/form-editar-fragment";
    }

    @GetMapping("/visitas/buscar")
    public String buscarVisitas(@RequestParam String search, Model model) {
        model.addAttribute("visitas", visitaService.searchByNCRFC(search));
        return "visitas/resultados-fragment";
    }

    @GetMapping("/proyectos/crear") 
    public String crearProyectoForm() {
        return "proyectos/crear-proyecto";
    }

    @GetMapping("/proyectos/editar")
    public String editarProyecto(Model model) {
        model.addAttribute("proyectosRecientes", proyectoService.getFirst5());
        return "proyectos/editar-proyecto";
    }

    @GetMapping("/proyectos/form-editar")
    public String getFormEditarProyectos(@RequestParam Integer id, Model model) {
        model.addAttribute("proyecto", proyectoService.getProyectoById(id));
        return "proyectos/form-editar-fragment";
    }

    @GetMapping("/proyectos/buscar")
    public String buscarProyectos(@RequestParam String search, Model model) {
        model.addAttribute("proyectos", proyectoService.searchByClave(search));
        return "proyectos/resultados-fragment";
    }

    @GetMapping("/asistencias/principal")
    public String principalAsistencias() {
        return "/asistencias/principal-asistencias";
    }

    @GetMapping("/asistencias/cambiar-estado")
    public String cambiarEstadoAsistencias(@RequestParam Integer id, Model model) {
        EquipoGetDTO equipo = equipoService.getEquipoById(id);

        model.addAttribute("equipo", equipo);
        return "asistencias/cambiar-estado-asistencias";
    }

    @GetMapping("/asistencias/registrar")
    public String registrarAsistencias(@RequestParam(required = false) Integer id, Model model) {
        model.addAttribute("idEquipo", id);
        return "/asistencias/registrar-asistencia"; 
    }
}
