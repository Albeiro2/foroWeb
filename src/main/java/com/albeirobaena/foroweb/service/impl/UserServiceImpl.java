package com.albeirobaena.foroweb.service.impl;


import com.albeirobaena.foroweb.entity.UserEntity;
import com.albeirobaena.foroweb.io.KeyResponse;
import com.albeirobaena.foroweb.io.UserRequest;
import com.albeirobaena.foroweb.io.UserResponse;
import com.albeirobaena.foroweb.repository.UserRepository;
import com.albeirobaena.foroweb.service.AuthenticationFacade;
import com.albeirobaena.foroweb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
                .map(UserEntity::getPublicId)
                .or(() -> userRepository.findByUserName(loggedInUser).map(UserEntity::getPublicId))
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
        UserEntity user = userRepository.findByPublicId(findByUserId())
                .orElseThrow(()->new RuntimeException("User not found"));
        return convertToResponse(user);

    }

    @Override
    public UserResponse getUser(String userPublicId) {
        UserEntity user = userRepository.findByPublicId(userPublicId)
                .orElseThrow(()->new RuntimeException("User not found"));
        return convertToResponse(user);
    }

    @Override
    public String getPublicId(String log) {
        return userRepository.findByEmail(log)
                .map(UserEntity::getPublicId)
                .or(() -> userRepository.findByUserName(log).map(UserEntity::getPublicId))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public KeyResponse getKey() {
        UserEntity user = userRepository.findByPublicId(findByUserId())
                .orElseThrow(()-> new RuntimeException("User not found"));
        return new KeyResponse(user.getKeyRecovery());
    }

    private UserEntity convertToEntity(UserRequest request){
       return UserEntity.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userName(request.getUserName())
                .publicId(UUID.randomUUID().toString())
                .name(request.getName())
                .description(request.getDescription())
                .keyRecovery(UUID.randomUUID().toString())
                .build();
    }

    private UserResponse convertToResponse(UserEntity userSaved){
       return UserResponse.builder()
                .publicId(userSaved.getPublicId())
                .email(userSaved.getEmail())
                .userName(userSaved.getUserName())
                .description(userSaved.getDescription())
                .name(userSaved.getName())
                .build();
    }
}
