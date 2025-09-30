package com.albeirobaena.foroweb.service;

import com.albeirobaena.foroweb.entity.UserEntity;
import com.albeirobaena.foroweb.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {
        // Primero intenta buscar por email
        Optional<UserEntity> userOpt = userRepository.findByEmail(input);

        if (userOpt.isEmpty()) {
            // Si no lo encuentra, busca por username
            userOpt = userRepository.findByUserName(input);
        }

        UserEntity user = userOpt.orElseThrow(
                () -> new UsernameNotFoundException("User not found with email or username: " + input)
        );

        return new User(
                user.getEmail(),
                user.getPassword(),
                Collections.emptyList()
        );
    }
}
