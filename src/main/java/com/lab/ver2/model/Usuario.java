package com.lab.ver2.model;

import org.hibernate.envers.Audited;

import jakarta.persistence.*;
import lombok.*;

@Audited
@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usr")
    private Integer id;

    @Column(name = "username", length = 50)
    private String userName;

    @Column(length = 120)
    private String nombre;

    @Column(length = 255)
    private String password;

    @Column(name = "es_admin")
    private boolean esAdmin;

    @Column(name = "activo")
    private boolean activo;
    
}
