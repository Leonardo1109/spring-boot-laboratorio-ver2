package com.lab.ver2.dto;

import java.time.LocalDateTime;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AsistenciaGetDTO {

    private Integer id;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSalida;
    private String observacion;
    private VisitaGetDTO visita;
    private EquipoGetDTO equipo;
    private ActividadGetDTO actividad;

}
