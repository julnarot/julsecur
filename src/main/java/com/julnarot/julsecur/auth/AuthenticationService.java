package com.julnarot.julsecur.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.julnarot.julsecur.config.JwtService;
import com.julnarot.julsecur.user.User;
import com.julnarot.julsecur.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(SignUpRequest request) {
        var user = User.builder()
        .firstname(request.getFirstName())
        .lastname(request.getLastName())
        .email(request.getEmail())
        .password(request.getPassword())
        .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        
        return AuthenticationResponse.builder()
        .token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(), 
                request.getPassword()
            )
        );

        var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse
            .builder()
            .token(jwtToken).build();
    }
    
       
}
