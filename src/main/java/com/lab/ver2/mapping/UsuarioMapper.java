package com.lab.ver2.mapping;

import java.util.List;

import org.mapstruct.*;
import com.lab.ver2.dto.*;
import com.lab.ver2.model.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioGetDTO toDto(Usuario usuario);
    List<UsuarioGetDTO> toDtos( List<Usuario> usuarios);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    Usuario toUsuario ( UsuarioPostDTO dto );

}
