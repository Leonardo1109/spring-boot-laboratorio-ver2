package com.lab.ver2.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActividadPostDTO {

    private Integer id;
    
    @NotBlank
    @Size(min = 5, max = 200)
    private String descripcion;

    @NotNull
    @Min(value = 0, message = "El valor debe ser mayor o igual a cero")
    private Integer horas;
}
