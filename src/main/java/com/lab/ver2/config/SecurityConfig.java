package com.lab.ver2.config;

import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
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
                
                // PUBLIC
                .requestMatchers("/login", "/css/**").permitAll()
                
                ///
                /// PANTALLAS ADMIN
                /// 
                .requestMatchers(
                     "/pantallas/equipos/**", 
                     "/pantallas/usuarios/**",
                     "/pantallas/visitas/crear",
                    "/pantallas/visitas/editar",
                    "/pantallas/visitas/editar/tabla",
                    "/pantallas/visitas/form-editar",
                    "/pantallas/proyectos/crear",
                    "/pantallas/proyectos/editar",
                    "/pantallas/proyectos/editar/tabla",
                    "/pantallas/proyectos/form-editar",
                    "/pantallas/asistencias/cambiar-estado"
                ).hasRole("ADMIN")

                ///
                /// PANTALLAS AUTENTICADO
                /// 
                .requestMatchers( 
                    "/pantallas",
                    "/pantallas/inicio",
                    "/pantallas/visitas/buscar",
                    "/pantallas/proyectos/buscar",
                    "/pantallas/asistencias/principal",
                    "/pantallas/asistencias/registrar",
                    "/pantallas/asistencias/mostrar",
                    "/pantallas/asistencias/mostrar/tabla"
                ).authenticated()
                
                ///
                /// API GET ADMIN
                /// 
                .requestMatchers(HttpMethod.GET,
                    "/api/asistencias",
                    "/api/asistencias/*",
                    "/api/carreras/**",
                    "/api/equipos/*",
                    "/api/estatus",
                    "/api/proyectos",
                    "/api/roles",
                    "/api/tipos-equipo",
                    "/api/usuarios",
                    "/api/usuarios/*",
                    "/api/visitas",
                    "/api/visitas/buscar"
                ).hasRole("ADMIN")
                                
                .requestMatchers(HttpMethod.GET, 
                    "/api/asistencias/equipo/activa/*",
                    "/api/equipos",
                    "/api/proyectos/*",
                    "/api/visitas/*"
                ).authenticated()

                ///
                /// POST ADMIN
                /// 
                .requestMatchers(HttpMethod.POST,
                    "/api/equipos",
                    "/api/proyectos",
                    "/api/usuarios",
                    "/api/visitas",
                    "/api/equipos/asistencia/*"
                ).hasRole("ADMIN")

                ///
                /// POST AUTENTICADO
                /// 
                .requestMatchers(HttpMethod.POST, 
                    "/api/asistencias"
                ).authenticated()

                ///
                /// PUT ADMIN
                /// 
                .requestMatchers(HttpMethod.PUT, 
                    "/api/equipos/*",
                    "/api/proyectos/*",
                    "/api/usuarios/*",
                    "/api/visitas/*"
                ).hasRole("ADMIN")
                
                ///
                /// PUT AUTENTICADO
                /// 
                .requestMatchers(HttpMethod.PUT, 
                    "/api/asistencias/*"
                ).authenticated()
                
                ///
                /// DELETE ADMIN
                /// 
                .requestMatchers(HttpMethod.DELETE,
                    "/api/equipos/*",
                    "/api/proyectos/*",
                    "/api/visitas/*"
                ).hasRole("ADMIN")
                
                ///
                /// DELETE AUTHENTICATED
                /// 
                .requestMatchers(HttpMethod.DELETE,
                     "/api/asistencias/*"
                ).authenticated()
                
                ///
                /// PATCH AUTHENTICATED
                /// 
                .requestMatchers(HttpMethod.PATCH, 
                    "/api/equipos/estatus"
                ).authenticated()
                
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


