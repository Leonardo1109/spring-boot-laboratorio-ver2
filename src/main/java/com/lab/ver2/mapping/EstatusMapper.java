package com.lab.ver2.mapping;

import java.util.List;

import org.mapstruct.Mapper;

import com.lab.ver2.dto.EstatusDTO;
import com.lab.ver2.model.Estatus;

@Mapper(componentModel = "spring")
public interface EstatusMapper {

    
    EstatusDTO toDto (Estatus estatus);

    List<EstatusDTO> toDtoList (List<Estatus> estatusList);

    // Importante revisar en caso de hacer un PostEstatus | Mejor cambiar a un postDTO)

}
