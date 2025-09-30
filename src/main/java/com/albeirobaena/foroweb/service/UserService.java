package com.albeirobaena.foroweb.service;


import com.albeirobaena.foroweb.io.UserRequest;
import com.albeirobaena.foroweb.io.UserResponse;

public interface UserService {

    UserResponse registerUser(UserRequest request);

    String findByUserId();

    String findByUserName();
}
