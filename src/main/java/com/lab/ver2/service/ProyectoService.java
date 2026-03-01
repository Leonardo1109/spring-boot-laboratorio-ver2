package com.lab.ver2.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lab.ver2.dto.*;
import com.lab.ver2.mapping.*;
import com.lab.ver2.model.*;
import com.lab.ver2.repository.*;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProyectoService {

    private final ProyectoMapper proyectoMapper;
    private final ProyectoRepository proyectoRepository;
    private final CarreraRepository carreraRepository;
    private final VisitaRepository visitaRepository;

    @Transactional
    public List<ProyectoGetDTO> getAllProyectos(){
        return proyectoMapper.toDtos(proyectoRepository.findAll());
    }

    @Transactional
    public ProyectoGetDTO getProyectoById(Integer id){
        return proyectoMapper.toDto(proyectoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Proyecto no encontrado")));
    }

    @Transactional
    public ProyectoGetDTO createProyecto(ProyectoPostDTO dto){
        Proyecto proyecto = proyectoMapper.toProyecto(dto);

        // visitas y carreras
        List<Visita> visitas = visitaRepository.findAllById(dto.getVisitasIds());
        List<Carrera> carreras = carreraRepository.findAllById(dto.getCarrerasIds());

        proyecto.setVisitas(visitas);
        proyecto.setCarreras(carreras);

        proyecto.getActividades().forEach(a -> {
            a.setId(null);
            a.setProyecto(proyecto);
        });

        return proyectoMapper.toDto(proyectoRepository.save(proyecto));
    }

    @Transactional
    public ProyectoGetDTO updateProyecto(ProyectoPostDTO dto, Integer id){
        Proyecto proyecto = proyectoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        proyecto.setNombre(dto.getNombre());
        proyecto.setDescripcion(dto.getDescripcion());
        proyecto.setObjetivos(dto.getObjetivos());
        proyecto.setClave(dto.getClave());

        // Actividades
        Map<Integer, Actividad> actuales = proyecto.getActividades()
            .stream()
            .collect(Collectors.toMap(Actividad::getId, a -> a));

        Set<Integer> idsRecibidos = new HashSet<>();
       
        for (ActividadPostDTO actDto : dto.getActividades()) {
            
            if (actDto.getId() != null) {

                Actividad existente = actuales.get(actDto.getId());

                if (existente == null) {
                    throw new RuntimeException("Actividad no pertenece al proyecto");
                }

                existente.setDescripcion(actDto.getDescripcion());
                existente.setHoras(actDto.getHoras());

                idsRecibidos.add(existente.getId());

            } else {
                Actividad nueva = new Actividad();
                nueva.setDescripcion(actDto.getDescripcion());
                nueva.setHoras(actDto.getHoras());
                nueva.setProyecto(proyecto);

                proyecto.getActividades().add(nueva);
            }
        }

        proyecto.getActividades().removeIf(a ->
            a.getId() != null &&
            !idsRecibidos.contains(a.getId())
        );

        //visitas y carreras
        // visitas
        List<Visita> visitas = visitaRepository.findAllById(dto.getVisitasIds());
        proyecto.getVisitas().clear();
        proyecto.getVisitas().addAll(visitas);

        // carreras
        List<Carrera> carreras = carreraRepository.findAllById(dto.getCarrerasIds());
        proyecto.getCarreras().clear();
        proyecto.getCarreras().addAll(carreras);

        return proyectoMapper.toDto(proyectoRepository.save(proyecto));        
    }

    @Transactional
    public void deleteProyecto(Integer id){
        if (!proyectoRepository.existsById(id)) {
            throw new RuntimeException("Proyecto no encontrado");
        }
        proyectoRepository.deleteById(id);;
    }

    public Page<Proyecto> buscarProyecto(String search, Pageable pageable) {

        if (search == null || search.isBlank()) {
            return proyectoRepository.findAll(pageable);
        }
        return proyectoRepository.searchComplete(search, pageable);
    }
    /*
    public List<ProyectoGetDTO> getFirst5(){
        return proyectoMapper.toDtos(proyectoRepository.findTop5ByOrderByIdDesc());
    }
    */

    public List<ProyectoGetDTO> searchByClave(String clave){
        return proyectoMapper.toDtos(proyectoRepository.findByClaveContainingIgnoreCase(clave));
    }
}
