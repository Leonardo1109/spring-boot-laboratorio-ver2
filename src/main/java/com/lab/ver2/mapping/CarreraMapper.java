package com.lab.ver2.mapping;

import java.util.List;

import org.mapstruct.Mapper;

import com.lab.ver2.dto.CarreraDTO;
import com.lab.ver2.model.Carrera;

@Mapper(componentModel = "spring")
public interface CarreraMapper {

    CarreraDTO toDto (Carrera carrera);
    
    List<CarreraDTO> toDtos (List<Carrera> carreras);

    // Agregar en caso de un post un CarreraPostDTO
}
