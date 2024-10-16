package com.techQuest.TechQuest.services.auth;

import com.riwi.hero_training.application.dtos.requests.AuthUserRequestDto;
import com.riwi.hero_training.application.dtos.responses.AuthUserResponseDto;
import com.riwi.hero_training.domain.entities.UserEntity;
import com.riwi.hero_training.domain.exceptions.InvalidCredentialException;
import com.riwi.hero_training.domain.ports.service.interfaces.IAuthService;
import com.riwi.hero_training.infrastructure.helpers.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService implements IAuthService {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private final JwtUtil jwtUtil;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthUserResponseDto login(AuthUserRequestDto request) {
        // Cargar el usuario a partir del identificador (nombre de usuario o correo)
        UserEntity user = (UserEntity) userDetailsService.loadUserByUsername(request.getIdentifier());

        //Verifica si las contraseña coinciden
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw  new InvalidCredentialException("Invalid credential");
        }

        //Realiza la autentificacion
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getIdentifier(), request.getPassword()));

        //Genera la respuesta de autentificacion del token JWT
        return AuthUserResponseDto.builder()
                .message(user.getRole() + "Succesfull authentication") // Mensajito
                .token(this.jwtUtil.generateToken(user))// Genera el token
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build();
    }
}
