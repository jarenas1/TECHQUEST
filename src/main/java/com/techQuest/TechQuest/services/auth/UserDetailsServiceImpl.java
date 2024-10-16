package com.techQuest.TechQuest.services.auth;

import com.riwi.hero_training.domain.entities.UserEntity;
import com.riwi.hero_training.infrastructure.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        //Busca usuario por nombre de usuario o correo
        UserEntity user = userRepository.findByUsernameOrEmail(identifier, identifier);

        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}
