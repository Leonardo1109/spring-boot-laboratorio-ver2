package com.lab.ver2.controller;

import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;    


import com.lab.ver2.dto.EquipoGetDTO; 
import com.lab.ver2.model.Asistencia;
import com.lab.ver2.model.Equipo;
import com.lab.ver2.model.Proyecto;
import com.lab.ver2.model.Usuario;
import com.lab.ver2.model.Visita;
import com.lab.ver2.service.*;

import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/pantallas")
@RequiredArgsConstructor
public class PantallasController {

    private final EquipoService equipoService;
    private final VisitaService visitaService;
    private final ProyectoService proyectoService;
    private final AsistenciaService asistenciaService;
    private final UsuarioService usuarioService;

    @GetMapping
    public String home() {
        return "index";  // página principal con sidebar
    }

    @GetMapping("/inicio")
    public String retornoHome() {
        return "inicio"; 
    }

    // ====================================================================================== //
    //                                          Equipo
    // ====================================================================================== //

    // Enrutamiento para crear un equipo
    @GetMapping("/equipos/crear") // ruta
    public String crearEquipoForm() {
        return "equipos/crear-equipo"; // HTML
    }

    // Paginar los equipos un parametro recibido
    @GetMapping("/equipos/editar")
    public String paginaEditarEquipo(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable,
            Model model) {

        Page<Equipo> pagina = equipoService.buscarEquipo(search, pageable);

        model.addAttribute("equiposRecientes", pagina.getContent());
        model.addAttribute("page", pagina);
        model.addAttribute("search", search);

        return "equipos/editar-equipo";
    }

    // Editar fragmento de tabla (HTMX)
    @GetMapping("/equipos/editar/tabla")
    public String tablaEquipos(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable,
            Model model) {

        Page<Equipo> pagina = equipoService.buscarEquipo(search, pageable);

        model.addAttribute("equiposRecientes", pagina.getContent());
        model.addAttribute("page", pagina);
        model.addAttribute("search", search);

        return "equipos/editar-equipo :: tablaContainer";
    }

    // Pagina para Editar por ID
    @GetMapping("/equipos/form-editar")
    public String getFormEditar(@RequestParam Integer id, Model model) {
        model.addAttribute("equipo", equipoService.getEquipoById(id));
        return "equipos/form-editar-fragment";
    }

    // ====================================================================================== //
    //                                          Visitante
    // ====================================================================================== //

    @GetMapping("/visitas/crear") // ruta
    public String crearVisitaForm() {
        return "visitas/crear-visita"; // HTML
    }

    @GetMapping("/visitas/editar")
    public String paginaEditarVisitas(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable,
            Model model) {

        Page<Visita> pagina = visitaService.buscarVisita(search, pageable);

        model.addAttribute("visitasRecientes", pagina.getContent());
        model.addAttribute("page", pagina);
        model.addAttribute("search", search);

        return "visitas/editar-visita";
    }

    // Editar fragmento HTMX
    @GetMapping("/visitas/editar/tabla")
    public String tablaVisitas(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable,
            Model model) {

        Page<Visita> pagina = visitaService.buscarVisita(search, pageable);

        model.addAttribute("visitasRecientes", pagina.getContent());
        model.addAttribute("page", pagina);
        model.addAttribute("search", search);

        return "visitas/editar-visita :: tablaContainer";
    }

    @GetMapping("/visitas/form-editar")
    public String getFormEditarVisitas(@RequestParam Integer id, Model model) {
        model.addAttribute("visita", visitaService.getVisitaById(id));
        return "visitas/form-editar-fragment";
    }

    @GetMapping("/visitas/buscar")
    public String buscarVisitas(@RequestParam String search, Model model) {
        model.addAttribute("visitas", visitaService.searchVisita(search));
        return "visitas/resultados-fragment";
    }

    // ====================================================================================== //
    //                                          Proyecto
    // ====================================================================================== //
    
    @GetMapping("/proyectos/crear") 
    public String crearProyectoForm() {
        return "proyectos/crear-proyecto";
    }

    @GetMapping("/proyectos/editar")
    public String paginaEditarProyectos(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable,
            Model model) {

        Page<Proyecto> pagina = proyectoService.buscarProyecto(search, pageable);

        model.addAttribute("proyectosRecientes", pagina.getContent());
        model.addAttribute("page", pagina);
        model.addAttribute("search", search);

        return "proyectos/editar-proyecto";
    }

    // Editar fragmento HTMX
    @GetMapping("/proyectos/editar/tabla")
    public String tablaProyectos(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable,
            Model model) {

        Page<Proyecto> pagina = proyectoService.buscarProyecto(search, pageable);

        model.addAttribute("proyectosRecientes", pagina.getContent());
        model.addAttribute("page", pagina);
        model.addAttribute("search", search);

        return "proyectos/editar-proyecto :: tablaContainer";
    }

    @GetMapping("/proyectos/form-editar")
    public String getFormEditarProyectos(@RequestParam Integer id, Model model) {
        model.addAttribute("proyectoId", id);
        return "proyectos/form-editar-fragment";
    }

    @GetMapping("/proyectos/buscar")
    public String buscarProyectos(@RequestParam String search, Model model) {
        model.addAttribute("proyectos", proyectoService.searchByClaveNombre(search));
        return "proyectos/resultados-fragment";
    }

    // ====================================================================================== //
    //                                          Asistencias
    // ====================================================================================== //

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

    //hx-get="/pantallas/asistencia/mostrar"
    @GetMapping("/asistencias/mostrar")
    public String mostrarAsistencias(
        @RequestParam(required = false) String search,
        @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC)
        Pageable pageable,
        Model model) {

        Page<Asistencia> pagina = asistenciaService.buscarAsistencia(search, pageable);
        model.addAttribute("asistenciasRecientes", pagina.getContent());
        model.addAttribute("page", pagina);
        model.addAttribute("search", search);
        return "/asistencias/mostrar-asistencias";
    }

    
    // Editar fragmento HTMX
    @GetMapping("/asistencias/mostrar/tabla")
    public String tablaAsistencias(
        @RequestParam(required = false) String search,
        @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC)
        Pageable pageable,
        Model model) {

            Page<Asistencia> pagina = asistenciaService.buscarAsistencia(search, pageable);
            model.addAttribute("asistenciasRecientes", pagina.getContent());
            model.addAttribute("page", pagina);
            model.addAttribute("search", search);

        return "/asistencias/mostrar-asistencias :: tablaContainer";
    }

    // ====================================================================================== //
    //                                          USUARIOS
    // ====================================================================================== //

    @GetMapping("/usuarios/crear") 
    public String crearUsuario() {
        return "usuarios/crear-usuario";
    }

    @GetMapping("/usuarios/principal")
    public String princpalUsuarios(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable,
            Model model) {

        Page<Usuario> pagina = usuarioService.buscarUsuario(search, pageable);

        model.addAttribute("usuarios", pagina.getContent());
        model.addAttribute("page", pagina);
        model.addAttribute("search", search);

        return "usuarios/principal-usuarios";
    }

    // Editar fragmento de tabla (HTMX)
    @GetMapping("/usuarios/principal/tabla")
    public String tablaUsuarios(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable,
            Model model) {

        Page<Usuario> pagina = usuarioService.buscarUsuario(search, pageable);

        model.addAttribute("usuarios", pagina.getContent());
        model.addAttribute("page", pagina);
        model.addAttribute("search", search);

        return "usuarios/principal-usuarios :: tablaContainer";
    }

    // Pagina para Editar por ID
    @GetMapping("/usuarios/editar")
    public String editarUsuarios(@RequestParam Integer id, Model model) {
        model.addAttribute("usuario", usuarioService.getById(id));
        return "usuarios/editar-usuario";
    }
    
}
