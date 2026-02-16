package com.lab.ver2.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioPostDTO {
    
    @NotBlank
    @Size(min = 5, max = 50)
    private String userName;

    @NotBlank
    @Size(min = 5, max = 120)
    private String nombre;

    @NotBlank
    @Size(min = 5, max = 255)
    private String password;

    private boolean esAdmin;
    
}
