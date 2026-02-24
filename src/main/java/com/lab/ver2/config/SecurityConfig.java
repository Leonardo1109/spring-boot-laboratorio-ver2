package com.lab.ver2.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                // pantallas protegidas para la vista
                //.requestMatchers("/pantallas/equipos/crear").hasRole("ADMIN")
                .requestMatchers("/pantallas/equipos/editar").hasRole("ADMIN")
                .requestMatchers("/pantallas/proyectos/crear").hasRole("ADMIN")
                .requestMatchers("/pantallas/proyectos/editar").hasRole("ADMIN")
                .requestMatchers("/pantallas/visitas/crear").hasRole("ADMIN")
                .requestMatchers("/pantallas/visitas/editar").hasRole("ADMIN")

                .requestMatchers("/login").permitAll()
                .requestMatchers("/api/carreras", "/api/tipos-equipo").permitAll()
                .requestMatchers("/api/admin-usuarios/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            ).logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
