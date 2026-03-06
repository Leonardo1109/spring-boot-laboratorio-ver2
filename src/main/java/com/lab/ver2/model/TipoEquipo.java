package com.lab.ver2.model;

import java.util.List;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Audited
@Entity
@Table(name = "tipo_equipo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoEquipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private Integer id;

    @Column(length = 120)
    private String descripcion;

    @OneToMany(mappedBy = "tipoEquipo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("tipoequipo_equipo_ref")
    private List<Equipo> equipos;
}
