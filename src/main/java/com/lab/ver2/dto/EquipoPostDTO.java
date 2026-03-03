package com.lab.ver2.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EquipoPostDTO {

    @NotBlank
    @Size(min = 5, max = 200)
    private String descripcion;

    @NotBlank
    @Size(min = 5, max = 100)
    private String ubicacion;

    @NotBlank
    @Size(min = 5, max = 50)
    private String codigoInventario;

    private String observaciones;

    @NotNull
    private Integer estatusId;

    @NotNull
    private Integer tipoEquipoId;
}
