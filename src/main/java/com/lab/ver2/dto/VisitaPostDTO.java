package com.lab.ver2.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VisitaPostDTO {

    @NotBlank
    @Size(min = 9, max = 13)
    private String noCuentaRFC;
    
    @NotBlank
    @Size(min = 3, max = 120)
    private String nombre;

    @NotBlank
    @Size(min = 3, max = 120)
    private String apellidoMaterno;
    
    @NotBlank
    @Size(min = 3, max = 120)
    private String apellidoPaterno;
    
    @Email
    private String email;
    
    @NotNull
    private Integer rolId;
    
    @NotNull
    private Integer carreraId;
}
