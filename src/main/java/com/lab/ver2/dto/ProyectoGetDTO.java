package com.lab.ver2.dto;

import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProyectoGetDTO {

    private Integer id;
    private String nombre;
    private String descripcion;
    private String objetivos;
    private String clave;
    private List<ActividadGetDTO> actividades;
    private List<VisitaGetDTO> visitas; // responsables
    private List<CarreraDTO> carreras;

}
