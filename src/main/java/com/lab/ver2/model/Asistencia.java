package com.lab.ver2.model;

import java.time.LocalDateTime;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

@Audited
@Entity
@Table(name = "asistencia")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "hora_entrada")
    private LocalDateTime horaEntrada;    

    @Column(name = "hora_salida")
    private LocalDateTime horaSalida;

    @Column(columnDefinition = "TEXT")
    private String observacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visita_id")
    @JsonBackReference("visita_asistencia_ref")
    private Visita visita;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipo_id")
    @JsonBackReference("equipo_asistencia_ref")
    private Equipo equipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actividad_id")
    @JsonBackReference("actividad_asistencia_ref")
    private Actividad actividad;
}
