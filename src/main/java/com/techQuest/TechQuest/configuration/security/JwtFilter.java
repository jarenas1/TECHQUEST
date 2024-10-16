package com.techQuest.TechQuest.configuration.security;

import com.riwi.hero_training.infrastructure.helpers.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //Obtiene el token del encabezado de autorization
        final String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;


        //Verifica si el token esta presente y tiene el prefijo "Bearer "
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            jwt = authHeader.substring(7); // Extrae el token
            username = jwtUtil.extractUsername(jwt); // Extrae el nombre de usuario mediante el token
        }

        //Si hay un usuario en el context de seguridad, significa que ya se autentico
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username); // Carga los detalles del usuario

            //Valida el token
            if(jwtUtil.validateToken(jwt, username)){
                //Crea una nueva authenticacion en el contexto de seguridad
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities() );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken); // Establece la autenticacion
            }
        }

        //Continua la cadena de filtros
        filterChain.doFilter(request,response);
    }
}
