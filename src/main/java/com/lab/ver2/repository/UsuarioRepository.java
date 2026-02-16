package com.lab.ver2.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lab.ver2.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    Optional<Usuario> findByUserName(String userName);
}
