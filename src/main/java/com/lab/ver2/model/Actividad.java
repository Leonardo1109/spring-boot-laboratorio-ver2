package com.lab.ver2.model;

import java.util.List;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Audited
@Entity
@Table(name = "actividad")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(length = 200)
    private String descripcion;

    private Integer horas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proyecto")
    @JsonBackReference("proyecto_actividad_ref")
    private Proyecto proyecto;

    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("actividad_asistencia_ref")
    private List<Asistencia> asistencias;
}
