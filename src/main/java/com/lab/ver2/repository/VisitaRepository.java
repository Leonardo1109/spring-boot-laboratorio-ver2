package com.lab.ver2.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.lab.ver2.model.Visita;

@Repository
public interface VisitaRepository extends JpaRepository<Visita, Integer>{
    List<Visita> findByNoCuentaRFCContainingIgnoreCase(String noCuentaRFC);
    //List<Visita> findTop5ByOrderByIdDesc(); // cambiar por la paginacion
    List<Visita> findByProyectos_Id(Integer proyectoId);

    @Query("""
        SELECT v FROM Visita v
        WHERE LOWER(v.noCuentaRFC) LIKE LOWER(CONCAT('%', :search, '%'))
           OR LOWER(v.nombre) LIKE LOWER(CONCAT('%', :search, '%'))
           OR LOWER(v.email) LIKE LOWER(CONCAT('%', :search, '%'))
           OR LOWER(v.apellidoPaterno) LIKE LOWER(CONCAT('%', :search, '%'))
           OR LOWER(v.apellidoMaterno) LIKE LOWER(CONCAT('%', :search, '%'))
    """)
    Page<Visita> search(String search, Pageable pageable); 
}
