package com.lab.ver2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lab.ver2.dto.*;
import com.lab.ver2.mapping.AsistenciaMapper;
import com.lab.ver2.model.*;
import com.lab.ver2.repository.*;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AsistenciaService {

    private final AsistenciaMapper asistenciaMapper;
    private final AsistenciaRepository asistenciaRepository;
    private final VisitaRepository visitaRepository;
    private final ActividadRepository actividadRepository;
    private final EquipoRepository equipoRepository;

    @Transactional
    public List<AsistenciaGetDTO> getAllAsistencias(){
        return asistenciaMapper.toDtos(asistenciaRepository.findAll());
    }

    @Transactional
    public AsistenciaGetDTO getAsistenciaById(Integer id){
        return asistenciaMapper.toDto(asistenciaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Asistencia no encontrada")));
    }

    @Transactional
    public AsistenciaGetDTO createAsistencia(AsistenciaPostDTO dto){
        Asistencia asistencia = asistenciaMapper.toAsistencia(dto);

        // visita equipo y actividad
        Visita visita = visitaRepository.findById(dto.getVisitaId())
            .orElseThrow(() -> new RuntimeException("Visita no encontrada"));

        Equipo equipo = equipoRepository.findById(dto.getEquipoId())
            .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        Actividad actividad = actividadRepository.findById(dto.getActividadId())
            .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));

        asistencia.setVisita(visita);
        asistencia.setEquipo(equipo);
        asistencia.setActividad(actividad);

        asistencia = asistenciaRepository.save(asistencia);
        return asistenciaMapper.toDto(asistencia);
    }

    @Transactional
    public AsistenciaGetDTO updateAsistencia(AsistenciaPostDTO dto, Integer id){
        Asistencia asistencia = asistenciaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Asistencia no encontrada"));

        asistencia.setHoraEntrada(dto.getHoraEntrada());
        asistencia.setHoraSalida(dto.getHoraSalida());
        asistencia.setObservacion(dto.getObservacion());
        
        // visita equipo y actividad
        Visita visita = visitaRepository.findById(dto.getVisitaId())
            .orElseThrow(() -> new RuntimeException("Visita no encontrada"));

        Equipo equipo = equipoRepository.findById(dto.getEquipoId())
            .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        Actividad actividad = actividadRepository.findById(dto.getActividadId())
            .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));

        asistencia.setVisita(visita);
        asistencia.setEquipo(equipo);
        asistencia.setActividad(actividad);

        asistencia = asistenciaRepository.save(asistencia);
        return asistenciaMapper.toDto(asistencia);
    }

    @Transactional
    public void deleteAsistencia(Integer id){
        if (!asistenciaRepository.existsById(id)) {
            throw new RuntimeException("Asistencia no encotnrada");
        }
        asistenciaRepository.deleteById(id);
    }
}
