package com.lab.ver2.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActividadPostDTO {

    @NotBlank
    @Size(min = 5, max = 200)
    private String descripcion;

    @NotNull
    private Integer horas;
}
