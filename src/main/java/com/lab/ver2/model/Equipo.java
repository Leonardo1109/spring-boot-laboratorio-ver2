package com.lab.ver2.model;

import java.util.List;

import com.fasterxml.jackson.annotation.*;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "equipo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(length = 200)
    private String descripcion;

    @Column(length = 100)
    private String ubicacion;

    @Column(name = "codigo_inventario", length = 50)
    private String codigoInventario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estatus")
    @JsonBackReference("estatus_equipo_ref")
    private Estatus estatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo")
    @JsonBackReference("tipoequipo_equipo_ref")
    private TipoEquipo tipoEquipo;

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("equipo_asistencia_ref")
    private List<Asistencia> asistencias;
}
