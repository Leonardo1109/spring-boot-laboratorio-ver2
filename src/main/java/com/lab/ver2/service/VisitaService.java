package com.lab.ver2.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lab.ver2.dto.*;
import com.lab.ver2.mapping.VisitaMapper;
import com.lab.ver2.model.*;
import com.lab.ver2.repository.*;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VisitaService {

    private final VisitaMapper visitaMapper;
    private final VisitaRepository visitaRepository;
    private final RolRepository rolRepository;
    private final CarreraRepository carreraRepository;

    @Transactional
    public List<VisitaGetDTO> getAllVisitas() {
        return visitaMapper.toDtos(visitaRepository.findAll());
    }

    @Transactional
    public VisitaGetDTO getVisitaById(Integer id) {
        return visitaMapper.toDto(visitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visita no encontrada")));
    }

    @Transactional
    public VisitaGetDTO createVisita(VisitaPostDTO dto) {
        Visita visita = visitaMapper.toVisita(dto);

        // rol y carrera
        Rol rol = rolRepository.findById(dto.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        Carrera carrera = carreraRepository.findById(dto.getCarreraId())
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        visita.setRol(rol);
        visita.setCarrera(carrera);

        visita = visitaRepository.save(visita);
        return visitaMapper.toDto(visita);
    }

    @Transactional
    public VisitaGetDTO updateVisita(VisitaPostDTO dto, Integer id) {
        Visita visita = visitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visita no encontrada"));

        // Rol y carrera
        Rol rol = rolRepository.findById(dto.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        Carrera carrera = carreraRepository.findById(dto.getCarreraId())
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        visita.setNoCuentaRFC(dto.getNoCuentaRFC());
        visita.setNombre(dto.getNombre());
        visita.setApellidoMaterno(dto.getApellidoMaterno());
        visita.setApellidoPaterno(dto.getApellidoPaterno());
        visita.setEmail(dto.getEmail());
        visita.setRol(rol);
        visita.setCarrera(carrera);

        visita = visitaRepository.save(visita);
        return visitaMapper.toDto(visita);
    }

    @Transactional
    public void deleteVisita(Integer id) {
        if (!visitaRepository.existsById(id)) {
            throw new RuntimeException("Visita no encontrada");
        }
        visitaRepository.deleteById(id);
    }

    public List<VisitaGetDTO> searchByNCRFC(String texto) {
        return visitaMapper.toDtos(
                visitaRepository.findByNoCuentaRFCContainingIgnoreCase(texto));
    }

    public List<Visita> getFirst5() {
        return visitaRepository.findTop5ByOrderByIdDesc();
    }

    public List<VisitaGetDTO> getVisitasByProyecto(Integer id){        
        return visitaMapper.toDtos(
            visitaRepository.findByProyectos_Id(id)
        );
    }
    
}
