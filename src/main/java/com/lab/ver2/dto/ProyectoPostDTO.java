package com.lab.ver2.dto;

import java.util.List;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProyectoPostDTO {
    
    @NotBlank
    @Size(min = 5, max = 150)
    private String nombre;

    @NotBlank
    @Size(min = 5)
    private String descripcion;
    
    @NotBlank
    @Size(min = 5)
    private String objetivos;

    @Size(min = 5, max = 30)
    private String clave;

    @NotNull
    private List<ActividadPostDTO> actividades;

    @NotNull
    private List<Integer> visitasIds; // responsables
    
    @NotNull
    private List<Integer> carrerasIds;
    
}
