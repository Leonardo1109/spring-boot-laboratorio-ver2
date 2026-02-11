package com.lab.ver2.mapping;

import java.util.List;
import org.mapstruct.*;

import com.lab.ver2.dto.*;
import com.lab.ver2.model.Asistencia;

@Mapper(componentModel = "spring")
public interface AsistenciaMapper {

    AsistenciaGetDTO toDto(Asistencia asistencia);
    List<AsistenciaGetDTO> toDtos(List<Asistencia> asistencias);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "visita", ignore = true)
    @Mapping(target = "equipo", ignore = true)
    @Mapping(target = "actividad", ignore = true)
    Asistencia toAsistencia(AsistenciaPostDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "visita", ignore = true)
    @Mapping(target = "equipo", ignore = true)
    @Mapping(target = "actividad", ignore = true)
    List<Asistencia> toAsistencias(List<AsistenciaPostDTO> dtos);
}
