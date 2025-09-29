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
      String loggedInUserEmail =  authenticationFacade.getAuthentication().getName();
      UserEntity loggedUser = userRepository.findByEmail(loggedInUserEmail).orElseThrow(() -> new RuntimeException("User not Found"));
      return loggedUser.getId();
    }

    private UserEntity convertToEntity(UserRequest request){
       return UserEntity.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userName(request.getUserName())
                .build();
    }

    private UserResponse convertToResponse(UserEntity userSaved){
       return UserResponse.builder()
                .id(userSaved.getId())
                .email(userSaved.getEmail())
                .userName(userSaved.getUserName())
                .build();
    }
}
