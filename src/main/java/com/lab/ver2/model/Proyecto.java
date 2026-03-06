package com.lab.ver2.model;

import java.util.List;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Audited
@Entity
@Table(name = "proyecto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proyecto")
    private Integer id;

    @Column(length = 150)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(columnDefinition = "TEXT")
    private String objetivos;

    @Column(length = 30)
    private String clave;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("proyecto_actividad_ref")
    private List<Actividad> actividades;


    @ManyToMany
    @JoinTable(
        name = "proyectos_responsables",
        joinColumns = @JoinColumn(name = "proyecto_id"),
        inverseJoinColumns = @JoinColumn(name = "visita_id")
    )
    private List<Visita> visitas;

    @ManyToMany
    @JoinTable(
        name = "proyectos_carreras",
        joinColumns = @JoinColumn(name = "proyecto_id"),
        inverseJoinColumns = @JoinColumn(name = "carrera_id")
    )
    private List<Carrera> carreras;
}
