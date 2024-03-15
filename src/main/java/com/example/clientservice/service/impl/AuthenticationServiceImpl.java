package com.example.clientservice.service.impl;

import com.example.clientservice.dto.AuthenticationRequest;
import com.example.clientservice.dto.AuthenticationResponse;
import com.example.clientservice.dto.RegisterRequest;
import com.example.clientservice.entity.Client;
import com.example.clientservice.entity.Role;
import com.example.clientservice.repository.ClientRepository;
import com.example.clientservice.service.AuthenticationService;
import com.example.clientservice.service.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса аутентификации.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var client = Client.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .pass(passwordEncoder.encode(request.getPassword()))
                .roles(Role.USER)
                .build();
        clientRepository.save(client);
        var jwtToken = jwtTokenService.generateToken(client);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var client = clientRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtTokenService.generateToken(client);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }
}
