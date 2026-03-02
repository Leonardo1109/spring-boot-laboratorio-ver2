package com.lab.ver2.dto;

import lombok.Data;

@Data
public class AdminFileDTO {
    private String userName;
    private String nombre;
    private String password;
    private boolean esAdmin;
}