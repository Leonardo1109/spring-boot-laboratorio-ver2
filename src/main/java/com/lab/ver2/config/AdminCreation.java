package com.lab.ver2.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lab.ver2.model.Usuario;
import com.lab.ver2.repository.UsuarioRepository;

@Configuration
public class AdminCreation {
    @Bean
    CommandLineRunner initAdmin(UsuarioRepository repo,
                                PasswordEncoder encoder) {
        return args -> {

            if (repo.findByUserName("admin").isEmpty()) {

                Usuario admin = Usuario.builder()
                        .userName("admin")
                        .nombre("Administrador")
                        .password(encoder.encode("admin123"))
                        .esAdmin(true)
                        .build();

                repo.save(admin);

                System.out.println("ADMIN creado");
            }
        };
    }

}
