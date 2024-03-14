package com.example.clientservice.controller;

import com.example.clientservice.dto.AuthenticationRequest;
import com.example.clientservice.dto.AuthenticationResponse;
import com.example.clientservice.dto.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * Интерфейс контроллера аутентификации.
 */
public interface AuthenticationController {

    /**
     * Обрабатывает запрос на регистрацию нового пользователя.
     *
     * @param request Запрос на регистрацию нового пользователя.
     * @return Ответ с JWT токеном, выданным при успешной регистрации.
     */
    @PostMapping("/registration")
    ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request);

    /**
     * Обрабатывает запрос на аутентификацию пользователя.
     *
     * @param request Запрос на аутентификацию пользователя.
     * @return Ответ с JWT токеном, выданным при успешной аутентификации.
     */
    @PostMapping("/login")
    ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request);
}
