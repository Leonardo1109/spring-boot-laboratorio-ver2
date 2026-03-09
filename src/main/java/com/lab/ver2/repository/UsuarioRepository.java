package com.lab.ver2.repository;

import java.util.Optional;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import com.lab.ver2.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    Optional<Usuario> findByUserName(String userName);

    @Query("""
        SELECT u FROM Usuario u
        WHERE LOWER(u.nombre) LIKE LOWER(CONCAT('%', :search, '%'))
           OR LOWER(u.userName) LIKE LOWER(CONCAT('%', :search, '%'))
    """)
    Page<Usuario> search(String search, Pageable pageable);   
}
