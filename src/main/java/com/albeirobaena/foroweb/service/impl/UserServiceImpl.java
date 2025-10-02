package com.albeirobaena.foroweb.service.impl;


import com.albeirobaena.foroweb.entity.UserEntity;
import com.albeirobaena.foroweb.io.UserRequest;
import com.albeirobaena.foroweb.io.UserResponse;
import com.albeirobaena.foroweb.repository.UserRepository;
import com.albeirobaena.foroweb.service.AuthenticationFacade;
import com.albeirobaena.foroweb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public UserResponse registerUser(UserRequest request) {
        UserEntity newUser = convertToEntity(request);
        var userSaved = userRepository.save(newUser);
        return convertToResponse(userSaved);
    }

    @Override
    public String findByUserId() {
        String loggedInUser = authenticationFacade.getAuthentication().getName();
        return userRepository.findByEmail(loggedInUser)
                .map(UserEntity::getSecondId)
                .or(() -> userRepository.findByUserName(loggedInUser).map(UserEntity::getSecondId))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public String findByUserName() {
        String loggedInUser = authenticationFacade.getAuthentication().getName();
        return userRepository.findByEmail(loggedInUser)
                .map(UserEntity::getUserName)
                .or(() -> userRepository.findByUserName(loggedInUser).map(UserEntity::getUserName))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserResponse getUserLogged() {
        UserEntity user = userRepository.findBySecondId(findByUserId())
                .orElseThrow(()->new RuntimeException("User not found"));
        return convertToResponse(user);

    }

    @Override
    public UserResponse getUser(String userSecondId) {
        UserEntity user = userRepository.findBySecondId(userSecondId)
                .orElseThrow(()->new RuntimeException("User not found"));
        return convertToResponse(user);
    }

    private UserEntity convertToEntity(UserRequest request){
       return UserEntity.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userName(request.getUserName())
                .secondId(UUID.randomUUID().toString())
                .build();
    }

    private UserResponse convertToResponse(UserEntity userSaved){
       return UserResponse.builder()
                .secondId(userSaved.getSecondId())
                .email(userSaved.getEmail())
                .userName(userSaved.getUserName())
                .build();
    }
}
