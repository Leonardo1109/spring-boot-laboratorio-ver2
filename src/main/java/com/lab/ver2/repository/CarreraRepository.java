package com.lab.ver2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab.ver2.model.Carrera;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Integer>{

}
