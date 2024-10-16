package com.techQuest.TechQuest.services.auth;


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
        UserEntity user = (UserEntity) userDetailsService.loadUserByUsername(request.getIdentifier()

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw  new InvalidCredentialException("Invalid credential");
        }

        //autenticar
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getIdentifier(), request.getPassword()));

        //jwt response
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
