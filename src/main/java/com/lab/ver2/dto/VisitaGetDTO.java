package com.lab.ver2.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VisitaGetDTO {

    private Integer id;
    private String noCuentaRFC;
    private String nombre;
    private String apellidoMaterno;
    private String apellidoPaterno;
    private String email;
    private RolDTO rol;
    private CarreraDTO carrera;

}
