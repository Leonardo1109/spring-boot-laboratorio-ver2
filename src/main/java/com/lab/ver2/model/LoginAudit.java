package com.lab.ver2.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "login_audit")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer usuarioId;

    private String username;

    private String event;

    private LocalDateTime fechaLogin;

}
