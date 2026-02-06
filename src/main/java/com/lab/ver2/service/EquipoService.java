package com.lab.ver2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lab.ver2.dto.*;
import com.lab.ver2.mapping.EquipoMapper;
import com.lab.ver2.model.*;
import com.lab.ver2.repository.*;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EquipoService {

    private final EquipoMapper equipoMapper;
    private final EquipoRepository equipoRepository;
    private final EstatusRepository estatusRepository;
    private final TipoEquipoRepository tipoEquipoRepository;

    @Transactional
    public List<EquipoGetDTO> getAllEquipos(){
        return equipoMapper.toDtoList(equipoRepository.findAll());
    }

    @Transactional
    public EquipoGetDTO getEquipoById(Integer id){
        return equipoMapper.toDto(equipoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Equipo no encontrado")));
    }

    @Transactional
    public EquipoGetDTO createEquipo(EquipoPostDTO dto){
        Equipo equipo = equipoMapper.toEquipo(dto);

        Estatus estatus = estatusRepository.findById(dto.getEstatusId())
            .orElseThrow(() -> new RuntimeException("Estatus no encontrado"));

        TipoEquipo tipoEquipo = tipoEquipoRepository.findById(dto.getTipoEquipoId())
            .orElseThrow(() -> new RuntimeException("Tipo de Equipo no encontrado"));

        equipo.setEstatus(estatus);
        equipo.setTipoEquipo(tipoEquipo);

        equipo = equipoRepository.save(equipo);

        return equipoMapper.toDto(equipo);
    }

    @Transactional
    public EquipoGetDTO updateEquipo(EquipoPostDTO dto, Integer id){
        Equipo equipo = equipoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        Estatus estatus = estatusRepository.findById(dto.getEstatusId())
            .orElseThrow(() -> new RuntimeException("Estatus no encontrado"));

        TipoEquipo tipoEquipo = tipoEquipoRepository.findById(dto.getTipoEquipoId())
            .orElseThrow(() -> new RuntimeException("Tipo de equipo no encontrado"));

        equipo.setDescripcion(dto.getDescripcion());
        equipo.setUbicacion(dto.getUbicacion());
        equipo.setCodigoInventario(dto.getCodigoInventario());
        equipo.setEstatus(estatus);
        equipo.setTipoEquipo(tipoEquipo);

        equipo = equipoRepository.save(equipo);
        return equipoMapper.toDto(equipo);
    }

    @Transactional
    public void deleteEquipo(Integer id){
        if (!equipoRepository.existsById(id)) {
            throw new RuntimeException("Equipo no encontrado");
        }
        equipoRepository.deleteById(id);
    }


    public List<EquipoGetDTO> searchByDescripcion(String texto) {
        return equipoMapper.toDtoList(
            equipoRepository.findByDescripcionContainingIgnoreCase(texto));
    }

    public List<Equipo> getFirst5() {
        return equipoRepository.findTop5ByOrderByIdDesc();
    }

    // cambiar estado
    @Transactional
    public EquipoGetDTO cambiarEstatus(Integer equipoId, Integer estatusId) {

        Equipo equipo = equipoRepository.findById(equipoId)
            .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        Estatus estatus = estatusRepository.findById(estatusId)
            .orElseThrow(() -> new RuntimeException("Estatus no encontrado"));

        equipo.setEstatus(estatus);

        return equipoMapper.toDto(equipoRepository.save(equipo));
    }
    
}
