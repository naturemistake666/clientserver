package com.example.clientservice.controller.Impl;

import com.example.clientservice.controller.AuthenticationController;
import com.example.clientservice.dto.AuthenticationRequest;
import com.example.clientservice.dto.AuthenticationResponse;
import com.example.clientservice.dto.RegisterRequest;
import com.example.clientservice.service.impl.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для обработки запросов аутентификации и регистрации пользователей.
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationController {

    private final AuthenticationServiceImpl authenticationServiceImpl;

    @Override
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationServiceImpl.register(request));
    }

    @Override
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationServiceImpl.authenticate(request));
    }
}
