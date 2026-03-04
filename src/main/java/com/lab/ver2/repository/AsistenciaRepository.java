package com.lab.ver2.repository;

import java.util.Optional;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.lab.ver2.model.Asistencia;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Integer>{
    Optional<Asistencia> findFirstByEquipoIdAndHoraSalidaIsNull(Integer equipoId);

    @Query("""
          SELECT DISTINCT a FROM Asistencia a
          JOIN a.equipo e
          JOIN a.visita v
          JOIN a.actividad ac
          JOIN ac.proyecto p
          WHERE LOWER(e.descripcion) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(e.ubicacion) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(e.codigoInventario) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(v.noCuentaRFC) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(v.nombre) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(v.apellidoPaterno) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(v.apellidoMaterno) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(p.descripcion) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(p.clave) LIKE LOWER(CONCAT('%', :search, '%'))
    """)
    Page<Asistencia> searchComplete(String search, Pageable pageable);

}
