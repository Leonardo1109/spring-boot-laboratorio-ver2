package com.lab.ver2.mapping;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.lab.ver2.dto.VisitaGetDTO;
import com.lab.ver2.dto.VisitaPostDTO;
import com.lab.ver2.model.Visita;

@Mapper(componentModel = "spring", uses = {RolMapper.class, CarreraMapper.class})
public interface VisitaMapper {

    // get
    VisitaGetDTO toDto (Visita visita);
    List<VisitaGetDTO> toDtos (List<Visita> visitas);

    // Post | asistencias, carrera, id, proyectos, rol
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rol", ignore = true)
    @Mapping(target = "carrera", ignore = true)
    @Mapping(target = "asistencias", ignore = true)
    @Mapping(target = "proyectos", ignore = true)
    Visita toVisita (VisitaPostDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rol", ignore = true)
    @Mapping(target = "carrera", ignore = true)
    @Mapping(target = "asistencias", ignore = true)
    @Mapping(target = "proyectos", ignore = true)
    List<Visita> toVisitas (List<VisitaPostDTO> dtos);
}
