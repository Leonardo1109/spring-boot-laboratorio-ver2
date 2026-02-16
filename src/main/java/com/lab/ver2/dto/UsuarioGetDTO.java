package com.lab.ver2.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UsuarioGetDTO {

    private Integer id;
    private String userName;
    private String nombre;
    private String password;
    private boolean esAdmin;

}
