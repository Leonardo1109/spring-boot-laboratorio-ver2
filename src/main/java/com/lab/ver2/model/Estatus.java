package com.lab.ver2.model;

import java.util.List;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Audited
@Entity
@Table(name = "estatus")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Estatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estatus")
    private Integer id;

    @Column(length = 120)
    private String descripcion;

    @OneToMany(mappedBy = "estatus", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("estatus_equipo_ref")
    private List<Equipo> equipos;
}
