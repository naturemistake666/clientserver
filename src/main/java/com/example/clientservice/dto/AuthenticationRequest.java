package com.example.clientservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, представляющий запрос на аутентификацию пользователя.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    /**
     * Адрес электронной почты пользователя.
     */
    private String email;

    /**
     * Пароль пользователя.
     */
    private String password;
}
