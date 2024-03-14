package com.example.clientservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, представляющий ответ аутентификации.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    /**
     * JWT токен, выданный пользователю при успешной аутентификации.
     */
    private String token;
}
