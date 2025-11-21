package com.lab.ver2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab.ver2.model.Equipo;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer>{
    List<Equipo> findByDescripcionContainingIgnoreCase(String descripcion);
}
