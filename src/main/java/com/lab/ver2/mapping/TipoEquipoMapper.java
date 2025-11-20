package com.lab.ver2.mapping;

import java.util.List;

import org.mapstruct.Mapper;

import com.lab.ver2.dto.TipoEquipoDTO;
import com.lab.ver2.model.TipoEquipo;

@Mapper(componentModel = "spring")
public interface TipoEquipoMapper {

    TipoEquipoDTO toDto (TipoEquipo tipoEquipo);

    List<TipoEquipoDTO> toDTOList (List<TipoEquipo> tipoEquipos);

    // En caso de hacer un post, mejor cambiar a un PostDTO
}
