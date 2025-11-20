package com.lab.ver2.mapping;

import java.util.List;

import org.mapstruct.*;

import com.lab.ver2.dto.*;
import com.lab.ver2.model.Proyecto;

@Mapper(componentModel = "spring", uses = {VisitaMapper.class, ActividadMapper.class, CarreraMapper.class})
public interface ProyectoMapper {

    //Get
    ProyectoGetDTO toDto(Proyecto proyecto);
    List<ProyectoGetDTO> toDtos(List<Proyecto> proyectos);

    //Post
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "visitas", ignore = true)
    @Mapping(target = "carreras", ignore = true)
    Proyecto toProyecto(ProyectoPostDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "visitas", ignore = true)
    @Mapping(target = "carreras", ignore = true)
    List<Proyecto> toProyectos(List<ProyectoPostDTO> dtos);
}
