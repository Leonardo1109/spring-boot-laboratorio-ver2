package com.lab.ver2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab.ver2.model.Asistencia;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Integer>{
    Optional<Asistencia> findFirstByEquipoIdAndHoraSalidaIsNull(Integer equipoId);

}
