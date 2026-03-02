package com.lab.ver2.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lab.ver2.model.Equipo;
import com.lab.ver2.model.Estatus;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer>{
    
    @Query("""
        SELECT e FROM Equipo e
        WHERE LOWER(e.descripcion) LIKE LOWER(CONCAT('%', :search, '%'))
           OR LOWER(e.ubicacion) LIKE LOWER(CONCAT('%', :search, '%'))
           OR LOWER(e.codigoInventario) LIKE LOWER(CONCAT('%', :search, '%'))
    """)
    Page<Equipo> search(String search, Pageable pageable);   
    
    List<Equipo> findDistinctByAsistenciasVisitaId(Integer id); // Delete Visita
    List<Equipo> findDistinctByAsistenciasActividadProyectoId(Integer id); // Delete proyecto
    List<Equipo> findDistinctByAsistenciasActividadId(Integer id); // Delete Actividad

    @Modifying
    @Query("""
        UPDATE Equipo e
        SET e.estatus = :estatus
        WHERE e.id IN :ids
    """)
    void updateEstadoDisponible(@Param("ids") List<Integer> ids,
                                @Param("estatus") Estatus estatus);
}
