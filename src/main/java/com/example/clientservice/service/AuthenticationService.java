package com.example.clientservice.service;

import com.example.clientservice.dto.AuthenticationRequest;
import com.example.clientservice.dto.AuthenticationResponse;
import com.example.clientservice.dto.RegisterRequest;

/**
 * Этот класс предоставляет реализации функциональности аутентификации и регистрации пользователя.
 * Он использует комбинацию репозиториев, кодировщиков паролей, служб JWT и менеджеров аутентификации для выполнения этих операций безопасно.
 */
public interface AuthenticationService {

    /**
     * Регистрирует нового пользователя в системе.
     * @param request объект RegisterRequest, содержащий данные нового пользователя
     * @return объект AuthenticationResponse, содержащий сгенерированный JWT токен
     */
    AuthenticationResponse register(RegisterRequest request);

    /**
     * Аутентифицирует пользователя в системе.
     * @param request объект AuthenticationRequest, содержащий email и пароль пользователя
     * @return объект AuthenticationResponse с сгенерированным JWT токеном
     */
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
