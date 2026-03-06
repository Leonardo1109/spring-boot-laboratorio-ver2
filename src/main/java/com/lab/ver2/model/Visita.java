package com.lab.ver2.model;

import java.util.List;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Audited
@Entity
@Table(name = "visita")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Visita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_visita")
    private Integer id;

    @Column(name = "no_cuenta_rfc", length = 15)
    private String noCuentaRFC;

    @Column(length = 120)
    private String nombre;

    @Column(name = "apellido_materno", length = 120)
    private String apellidoMaterno;

    @Column(name = "apellido_paterno", length = 120)
    private String apellidoPaterno;

    @Column(length = 100)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol")
    @JsonBackReference("rol_visita_ref")
    private Rol rol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_carrera")
    @JsonBackReference("carrera_visita_ref")
    private Carrera carrera;

    @OneToMany(mappedBy = "visita", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("visita_asistencia_ref")
    private List<Asistencia> asistencias;

    @ManyToMany(mappedBy = "visitas")
    private List<Proyecto> proyectos;
}
