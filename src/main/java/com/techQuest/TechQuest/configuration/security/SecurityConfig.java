package com.techQuest.TechQuest.configuration.security;

import com.riwi.hero_training.domain.enums.Roles;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity // Esta anotacion sirve para habilitar la configuracion de seguridad en la web para la aplicacion.
@Configuration // Indica que esta clase proporciona configuracion de spring. (Bean)
@AllArgsConstructor
public class SecurityConfig {
    //Filtros de JWT y proveedor de autentificacion
    @Autowired
    private final JwtFilter jwtFilter;

    @Autowired
    private final AuthenticationProvider authenticationProvider;

    //Rutas publicas
    private final String [] PUBLIC_ENDPOINT = {
            "/auth/login",
            "/users/register/student",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/skills/create"
    };

    //Rutas privadas
    private final String [] ADMIN_ENDPOINT = {
            "/users/**",
            "/users/register/admin",
            "/users/register/teacher",
            "/missions/**"
    };

    private final String [] TEACHER_ENDPOINT = {
            "/missions/**"
    };

    //Configuracion del SecurityFilterChain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Desactiva la proteccion CSFR
                /*
                    CSRF se utiliza para evitar ataques en los que se engaña a los usuarios para que realicen acciones
                    no deseadas. En aplicaciones que no usan sesiones o que usan tokens JWT, se suele desactivar
                    porque las solicitudes no dependen de la sesión almacenada en el navegador.
                */
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(ADMIN_ENDPOINT).hasAuthority(Roles.ADMIN.name()) // Requiere autoridad de admin para ADMIN_ENDPOINT
                        .requestMatchers(TEACHER_ENDPOINT).hasAuthority(Roles.TEACHER.name()) // Requiere autoridad de teacher para TEACHER_ENDPOINT
                        .requestMatchers(PUBLIC_ENDPOINT).permitAll() // Permite acceso a PUBLIC_ENDPOINT sin authentificacion
                        .anyRequest().authenticated() //Requiere autenticacion para cualquier otra solicitud
                )
                .authenticationProvider(authenticationProvider) //Proveedor de autentificacion
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Establece politica de sesion sin estado
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Añade el filtro JWT antes del filtro de authentificacion
                .build();
    }
}
