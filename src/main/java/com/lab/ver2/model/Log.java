package com.lab.ver2.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "log")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "tabla_afectada", length = 50)
    private String tablaAfectada;

    @Column(name = "registro_afectado", length = 50)
    private String registroAfectado;

    @Column(length = 10)
    private String accion;

    @Column(name = "usr_id")
    private Integer administradorId;

    private LocalDateTime fecha;

    @Column(columnDefinition = "JSON")
    private String cambios;
}
