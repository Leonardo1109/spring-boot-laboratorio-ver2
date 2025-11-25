package com.lab.ver2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab.ver2.model.Visita;

@Repository
public interface VisitaRepository extends JpaRepository<Visita, Integer>{
    List<Visita> findByNoCuentaRFCContainingIgnoreCase(String noCuentaRFC);
    List<Visita> findTop5ByOrderByIdDesc();
}
