package com.lab.ver2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lab.ver2.model.Equipo;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer>{
    /*
    List<Equipo> findByDescripcionContainingIgnoreCase(String descripcion);
    List<Equipo> findTop5ByOrderByIdDesc();
    */

    @Query("""
        SELECT e FROM Equipo e
        WHERE LOWER(e.descripcion) LIKE LOWER(CONCAT('%', :search, '%'))
           OR LOWER(e.ubicacion) LIKE LOWER(CONCAT('%', :search, '%'))
           OR LOWER(e.codigoInventario) LIKE LOWER(CONCAT('%', :search, '%'))
    """)
    Page<Equipo> search(String search, Pageable pageable);    

}
