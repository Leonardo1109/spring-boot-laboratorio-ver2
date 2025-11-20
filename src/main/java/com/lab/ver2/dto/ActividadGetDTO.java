package com.lab.ver2.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActividadGetDTO {

    private Integer id;
    private String descripcion;
    private Integer horas;
}
