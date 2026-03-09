/*
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
                

                .requestMatchers(HttpMethod.GET, "/api/asistencias").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/asistencias/*").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/asistencias/equipo/activa/*").authenticated()

                .requestMatchers(HttpMethod.GET, "/api/carreras").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/carreras/options").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/carreras/options-checkbox").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "/api/equipos").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/equipos/*").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "/api/estatus").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "/pantallas").authenticated()
                .requestMatchers(HttpMethod.GET, "/pantallas/inicio").authenticated()
                .requestMatchers(HttpMethod.GET, "/pantallas/equipos/crear").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/pantallas/equipos/editar").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/pantallas/equipos/editar/tabla").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/pantallas/equipos/form-editar").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/pantallas/visitas/crear").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/pantallas/visitas/editar").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/pantallas/visitas/editar/tabla").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/pantallas/visitas/form-editar").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/pantallas/visitas/buscar").authenticated()
                .requestMatchers(HttpMethod.GET, "/pantallas/proyectos/crear").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/pantallas/proyectos/editar").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/pantallas/proyectos/editar/tabla").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/pantallas/proyectos/form-editar").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/pantallas/proyectos/buscar").authenticated()
                .requestMatchers(HttpMethod.GET, "/pantallas/asistencias/principal").authenticated()
                .requestMatchers(HttpMethod.GET, "/pantallas/asistencias/cambiar-estado").authenticated()
                .requestMatchers(HttpMethod.GET, "/pantallas/asistencias/registrar").authenticated()
                .requestMatchers(HttpMethod.GET, "/pantallas/asistencias/mostrar").authenticated()
                .requestMatchers(HttpMethod.GET, "/pantallas/asistencias/mostrar/tabla").authenticated()
                .requestMatchers(HttpMethod.GET, "/pantallas/usuarios/crear").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/pantallas/usuarios/principal").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/pantallas/usuarios/principal/tabla").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/pantallas/usuarios/editar").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "/api/proyectos").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/proyectos/*").authenticated()

                .requestMatchers(HttpMethod.GET, "/api/roles").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "/api/tipos-equipo").hasRole("ADMIN")
                
                .requestMatchers(HttpMethod.GET, "/api/usuarios").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/usuarios/*").hasRole("ADMIN")
                
                .requestMatchers(HttpMethod.GET, "/api/visitas").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/visitas/*").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/visitas/buscar").hasRole("ADMIN")

                ///////////////////////////////////////////////////////////////////////
                .requestMatchers(HttpMethod.POST, "/api/asistencias").authenticated()

                .requestMatchers(HttpMethod.POST, "/api/equipos").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/equipos/asistencia/*").hasRole("ADMIN")

                .requestMatchers(HttpMethod.POST, "/api/proyectos").hasRole("ADMIN")

                .requestMatchers(HttpMethod.POST, "/api/usuarios").hasRole("ADMIN")

                .requestMatchers(HttpMethod.POST, "/api/visitas").hasRole("ADMIN")

                ///////////////////////////////////////////////////////////////////////
                .requestMatchers(HttpMethod.PUT, "/api/asistencias/*").authenticated()
                
                .requestMatchers(HttpMethod.PUT, "/api/equipos/*").hasRole("ADMIN")

                .requestMatchers(HttpMethod.PUT, "/api/proyectos/*").hasRole("ADMIN")

                .requestMatchers(HttpMethod.PUT, "/api/usuarios/*").hasRole("ADMIN")

                .requestMatchers(HttpMethod.PUT, "/api/visitas/*").hasRole("ADMIN")

                ///////////////////////////////////////////////////////////////////////
                .requestMatchers(HttpMethod.DELETE, "/api/asistencias/*").authenticated()
                
                .requestMatchers(HttpMethod.DELETE, "/api/equipos/*").hasRole("ADMIN")

                .requestMatchers(HttpMethod.DELETE, "/api/proyectos/*").hasRole("ADMIN")

                .requestMatchers(HttpMethod.DELETE, "/api/visitas/*").hasRole("ADMIN")

                ///////////////////////////////////////////////////////////////////////
                .requestMatchers(HttpMethod.PATCH, "/api/equipos/estatus").authenticated()

                // pantallas

                // api
            
                // publico
                .requestMatchers("/login").permitAll()
                .requestMatchers("/css/**").permitAll()

                // autenticado
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
 */
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
                    "/pantallas/proyectos/form-editar"
                ).hasRole("ADMIN")

                ///
                /// PANTALLAS AUTENTICADO
                /// 
                .requestMatchers( 
                    "/pantallas",
                    "/pantallas/inicio",
                    "/pantallas/visitas/buscar",
                    "/pantallas/proyectos/buscar",
                    "/pantallas/asistencias/**"
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


