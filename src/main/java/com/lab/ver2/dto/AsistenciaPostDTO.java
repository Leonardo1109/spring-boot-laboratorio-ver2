package com.lab.ver2.dto;

import lombok.*;

import java.time.LocalDateTime;

import jakarta.validation.constraints.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AsistenciaPostDTO {
    
    @FutureOrPresent
    private LocalDateTime horaEntrada;

    @FutureOrPresent
    private LocalDateTime horaSalida;

    @Size(min = 5)
    private String observacion;

    @NotNull
    private Integer visitaId;

    @NotNull
    private Integer equipoId;

    @NotNull
    private Integer actividadId;
}
