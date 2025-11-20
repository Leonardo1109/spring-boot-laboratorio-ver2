package com.lab.ver2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab.ver2.model.Actividad;
import com.lab.ver2.model.Proyecto;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Integer>{
    List<Actividad> findAllByProyecto(Proyecto proyecto);
}
