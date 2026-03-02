package com.lab.ver2.config;

import java.io.File;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.ver2.dto.AdminFileDTO;
import com.lab.ver2.model.Usuario;
import com.lab.ver2.repository.UsuarioRepository;

@Configuration
public class AdminCreation {
    @Bean
    CommandLineRunner initAdmin(UsuarioRepository repo,
                                PasswordEncoder encoder) {
        return args -> {

            File file = new File("./admin.json");

            if (!file.exists()) {
                System.out.println("Archivo admin.json no encontrado");
                return;
            }

            ObjectMapper mapper = new ObjectMapper();
            AdminFileDTO data = mapper.readValue(file, AdminFileDTO.class);

            if (repo.findByUserName(data.getUserName()).isEmpty()) {

                Usuario admin = Usuario.builder()
                        .userName(data.getUserName())
                        .nombre(data.getNombre())
                        .password(encoder.encode(data.getPassword()))
                        .esAdmin(data.isEsAdmin())
                        .build();

                repo.save(admin);

                System.out.println("ADMIN creado desde Archivo Json");
            }
        };
    }

}
