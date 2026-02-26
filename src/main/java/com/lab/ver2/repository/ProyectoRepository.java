package com.lab.ver2.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lab.ver2.model.Proyecto;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer>{
    //List<Proyecto> findTop5ByOrderByIdDesc();
    List<Proyecto> findByClaveContainingIgnoreCase(String clave);


    @Query("""
          SELECT DISTINCT p FROM Proyecto p
          LEFT JOIN p.visitas v
          LEFT JOIN p.carreras c
          WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :search, '%'))
               OR LOWER(p.clave) LIKE LOWER(CONCAT('%', :search, '%'))
               OR LOWER(v.noCuentaRFC) LIKE LOWER(CONCAT('%', :search, '%'))
               OR LOWER(v.nombre) LIKE LOWER(CONCAT('%', :search, '%'))
               OR LOWER(v.apellidoPaterno) LIKE LOWER(CONCAT('%', :search, '%'))
               OR LOWER(v.apellidoMaterno) LIKE LOWER(CONCAT('%', :search, '%'))
               OR LOWER(c.nombre) LIKE LOWER(CONCAT('%', :search, '%'))
    """)
    Page<Proyecto> searchComplete(String search, Pageable pageable);
    
    

    /*
    @Query(
			value = """
        SELECT a
        FROM Alumno a
        WHERE LOWER(a.nombre) LIKE LOWER(CONCAT('%', :texto, '%'))
           OR LOWER(a.paterno) LIKE LOWER(CONCAT('%', :texto, '%'))
           OR LOWER(CONCAT(a.nombre, ' ', COALESCE(a.paterno, '')))
                LIKE LOWER(CONCAT('%', :texto, '%'))
           OR LOWER(CONCAT(COALESCE(a.paterno, ''), ' ', a.nombre))
                LIKE LOWER(CONCAT('%', :texto, '%'))
        """
	)
	List<Alumno> buscarPorNombreFlexible(
			@Param("texto") String texto
	);
     */
}
