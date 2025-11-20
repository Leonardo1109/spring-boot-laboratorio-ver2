package com.lab.ver2.mapping;

import java.util.List;

import org.mapstruct.*;

import com.lab.ver2.dto.*;
import com.lab.ver2.model.Actividad;

@Mapper(componentModel = "spring")
public interface ActividadMapper {

    // get
    ActividadGetDTO toDto (Actividad actividad);
    List<ActividadGetDTO> toDtos(List<Actividad> actividades);

    // post | id, proyecto, asistencias
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "proyecto", ignore = true)
    @Mapping(target = "asistencias", ignore = true)
    Actividad toActividad (ActividadPostDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "proyecto", ignore = true)
    @Mapping(target = "asistencias", ignore = true)
    List<Actividad> toActividades(List<ActividadPostDTO> dtos);
}
