package com.lab.ver2.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EquipoGetDTO {

    private Integer id;
    private String descripcion;
    private String ubicacion;
    private String codigoInventario;
    private EstatusDTO estatus;
    private TipoEquipoDTO tipoEquipo;

}
