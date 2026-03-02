package com.lab.ver2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lab.ver2.dto.*;
import com.lab.ver2.mapping.AsistenciaMapper;
import com.lab.ver2.mapping.EquipoMapper;
import com.lab.ver2.model.*;
import com.lab.ver2.repository.*;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EquipoService {

    private final EquipoMapper equipoMapper;
    private final AsistenciaMapper asistenciaMapper;
    private final EquipoRepository equipoRepository;
    private final EstatusRepository estatusRepository;
    private final TipoEquipoRepository tipoEquipoRepository;
    private final AsistenciaRepository asistenciaRepository;
    private final VisitaRepository visitaRepository;
    private final ActividadRepository actividadRepository;

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


    public Page<Equipo> buscarEquipo(String search, Pageable pageable) {

        if (search == null || search.isBlank()) {
            return equipoRepository.findAll(pageable);
        }
        return equipoRepository.search(search, pageable);
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

    @Transactional
    public void editarAsistenciaPorEquipo(AsistenciaPostDTO dto, Integer equipoId, Integer estatusId ){
        Equipo equipo = equipoRepository.findById(equipoId)
            .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        Estatus estatus = estatusRepository.findById(estatusId)
            .orElseThrow(() -> new RuntimeException("Estatus no encontrado"));

        Optional<Asistencia> asistenciaOpt =
            asistenciaRepository.findFirstByEquipoIdAndHoraSalidaIsNull(equipoId);

        Visita visita = visitaRepository.findById(dto.getVisitaId())
            .orElseThrow(() -> new RuntimeException("Visita no encontrada"));

        Actividad actividad = actividadRepository.findById(dto.getActividadId())
            .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));
        
        if (asistenciaOpt.isPresent()) {
            Asistencia asistencia = asistenciaOpt.get();
            asistencia.setHoraEntrada(dto.getHoraEntrada());
            asistencia.setHoraSalida(dto.getHoraSalida());
            asistencia.setObservacion(dto.getObservacion());
            asistencia.setVisita(visita);
            asistencia.setEquipo(equipo);
            asistencia.setActividad(actividad);
            
            asistenciaRepository.save(asistencia);
        } else {
            // CREAR nueva asistencia
            Asistencia asistencia = asistenciaMapper.toAsistencia(dto);
            asistencia.setVisita(visita);
            asistencia.setEquipo(equipo);
            asistencia.setActividad(actividad);
            asistenciaRepository.save(asistencia);
        }
    
        // ACTUALIZAR estado del equipo (SIEMPRE)
        equipo.setEstatus(estatus);
        equipoRepository.save(equipo);
    }

    // Method if Visita, Proyecto, Actividad or Asistencia are deleted
    @Transactional
    public void changeStatusByDelete(String entityDeleted, Integer id){
        if ("visita".equals(entityDeleted)) {
            List<Equipo> equiposToChange = equipoRepository.findDistinctByAsistenciasVisitaId(id);
            iteracionCambiarEstadoEquipo(equiposToChange);
        }
        if ("proyecto".equals(entityDeleted)) {
            List<Equipo> equiposToChange = equipoRepository.findDistinctByAsistenciasActividadProyectoId(id);
            iteracionCambiarEstadoEquipo(equiposToChange);
        } 
        if ("actividad".equals(entityDeleted)) {
            List<Equipo> equiposToChange = equipoRepository.findDistinctByAsistenciasActividadId(id);
            iteracionCambiarEstadoEquipo(equiposToChange);
        }
    }

    public void iteracionCambiarEstadoEquipo(List<Equipo> equiposACambiar) {
        if (equiposACambiar.isEmpty() || equiposACambiar == null) return;
        List<Integer> ids = equiposACambiar.stream()
            .map(Equipo::getId)
            .toList();
        Estatus disponible = estatusRepository.findById(1)
            .orElseThrow(() -> new RuntimeException("Estatus no encontrado"));
        
        equipoRepository.updateEstadoDisponible(ids, disponible);
    }
    
}
