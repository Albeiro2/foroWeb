package com.albeirobaena.foroweb.controller;

import com.albeirobaena.foroweb.io.AuthenticationRequest;
import com.albeirobaena.foroweb.io.AuthenticationResponse;
import com.albeirobaena.foroweb.service.AppUserDetailsService;
import com.albeirobaena.foroweb.service.UserService;
import com.albeirobaena.foroweb.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AppUserDetailsService userDetailsService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLog(),request.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getLog());
        final String jwtToken = jwtUtil.generateToken(userDetails);
        String publicId = userService.getPublicId(jwtUtil.extractUsername(jwtToken));
        return new AuthenticationResponse(jwtToken,publicId);
    }
}
