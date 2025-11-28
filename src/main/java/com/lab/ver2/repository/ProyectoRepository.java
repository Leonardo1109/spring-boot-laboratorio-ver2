package com.lab.ver2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab.ver2.model.Proyecto;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer>{
    List<Proyecto> findTop5ByOrderByIdDesc();
    List<Proyecto> findByClaveContainingIgnoreCase(String clave);
}
