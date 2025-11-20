package com.lab.ver2.service;

import java.util.List;

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
    private final ActividadMapper actividadMapper;
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

        proyecto.getActividades().forEach(a -> a.setProyecto(proyecto));

        Proyecto proyectoGuardado = proyectoRepository.save(proyecto);
        return proyectoMapper.toDto(proyectoGuardado);

    }

    @Transactional
    public ProyectoGetDTO updateProyecto(ProyectoPostDTO dto, Integer id){
        Proyecto proyecto = proyectoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        proyecto.setNombre(dto.getNombre());
        proyecto.setDescripcion(dto.getDescripcion());
        proyecto.setObjetivos(dto.getObjetivos());
        proyecto.setClave(dto.getClave());

        // actividades
        List<Actividad> nuevas = actividadMapper.toActividades(dto.getActividades());
        proyecto.getActividades().clear(); 
        nuevas.forEach(a -> a.setProyecto(proyecto));
        proyecto.getActividades().addAll(nuevas);

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
}
