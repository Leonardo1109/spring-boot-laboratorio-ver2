package com.lab.ver2.mapping;

import java.util.List;

import org.mapstruct.Mapper;

import com.lab.ver2.dto.RolDTO;
import com.lab.ver2.model.Rol;

@Mapper(componentModel = "spring")
public interface RolMapper {

    RolDTO toDto(Rol rol);

    List<RolDTO> toDtos(List<Rol> rols);

    // agregar en caso de hacer un post rol
}
