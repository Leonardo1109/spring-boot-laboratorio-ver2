package com.lab.ver2.mapping;

import java.util.List;

import org.mapstruct.*;

import com.lab.ver2.dto.*;
import com.lab.ver2.model.Equipo;

@Mapper(componentModel = "spring", uses = {EstatusMapper.class, TipoEquipoMapper.class})
public interface EquipoMapper {

    // get
    EquipoGetDTO toDto (Equipo equipo);
    List<EquipoGetDTO> toDtoList (List<Equipo> equipos);

    // post
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estatus", ignore = true)
    @Mapping(target = "tipoEquipo", ignore = true)
    @Mapping(target = "asistencias", ignore = true)
    Equipo toEquipo (EquipoPostDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estatus", ignore = true)
    @Mapping(target = "tipoEquipo", ignore = true)
    @Mapping(target = "asistencias", ignore = true)
    List<Equipo> toEquipos (List<EquipoPostDTO> dtos);
}
