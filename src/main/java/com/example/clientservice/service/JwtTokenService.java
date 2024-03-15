package com.example.clientservice.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

/**
 * Интерфейс JwtTokenService определяет методы для работы с JWT-токенами доступа.
 *
 * Этот интерфейс предоставляет методы для извлечения информации из токенов, генерирации новых токенов,
 * а также методы для проверки их на валидность и срок действия.
 *
 * @author v.kindyalov
 */
public interface JwtTokenService {

    /**
     * Извлекает имя пользователя из JWT-токена доступа.
     *
     * @param token JWT-токен доступа.
     * @return Имя пользователя, извлеченное из токена.
     */
    String extractUsername(String token);

    /**
     * Извлекает определенное утверждение из JWT-токена доступа.
     *
     * @param token          JWT-токен доступа.
     * @param claimsResolver Функция для извлечения желаемого утверждения из утверждений токена.
     * @param <T>            Тип утверждения.
     * @return Извлеченное утверждение.
     */
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    /**
     * Генерирует JWT-токен доступа для предоставленного UserDetails.
     *
     * @param userDetails UserDetails, для которого генерируется токен.
     * @return Сгенерированный JWT-токен доступа.
     */
    String generateToken(UserDetails userDetails);

    /**
     * Генерирует JWT-токен доступа с дополнительными утверждениями для предоставленного UserDetails.
     *
     * @param extraClaims  Дополнительные утверждения, которые должны быть включены в токен.
     * @param userDetails  UserDetails, для которого генерируется токен.
     * @return Сгенерированный JWT-токен доступа.
     */
    String generateToken(Map<String,Object> extraClaims, UserDetails userDetails);

    /**
     * Проверяет, действителен ли предоставленный токен для данного UserDetails.
     *
     * @param token        JWT-токен для проверки.
     * @param userDetails UserDetails, с которыми проводится проверка.
     * @return true, если токен действителен для UserDetails, в противном случае false.
     */
    boolean isTokenValid(String token, UserDetails userDetails);

    /**
     * Проверяет, истек ли предоставленный JWT-токен доступа.
     *
     * @param token JWT-токен для проверки на истечение срока действия.
     * @return true, если токен истек, в противном случае false.
     */
    boolean isTokenExpired(String token);

    /**
     * Извлекает дату истечения срока действия из JWT-токена доступа.
     *
     * @param token JWT-токен доступа.
     * @return Дата истечения срока действия, извлеченная из токена.
     */
    Date extractExpiration(String token);

    /**
     * Извлекает все утверждения из JWT-токена доступа, используя предоставленный секретный ключ.
     *
     * @param token     JWT-токен доступа.
     * @param secretKey Секретный ключ, используемый для проверки токена.
     * @return Все утверждения, извлеченные из токена.
     */
    Claims extractAllClaims(String token, Key secretKey);
}
