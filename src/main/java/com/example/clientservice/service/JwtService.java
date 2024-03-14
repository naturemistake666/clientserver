package com.example.clientservice.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

/**
 * Интерфейс для работы с JWT токенами.
 */
public interface JwtService {

    /**
     * Извлекает имя пользователя из JWT токена.
     *
     * @param token JWT токен.
     * @return Имя пользователя, извлеченное из токена.
     */
    String extractUsername(String token);

    /**
     * Извлекает определенное утверждение из JWT токена.
     *
     * @param token          JWT токен.
     * @param claimsResolver Функция для извлечения утверждения из объекта Claims.
     * @return Извлеченное утверждение.
     */
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    /**
     * Генерирует JWT токен для пользователя.
     *
     * @param userDetails Данные пользователя.
     * @return Сгенерированный JWT токен.
     */
    String generateToken(UserDetails userDetails);

    /**
     * Генерирует JWT токен для пользователя с дополнительными утверждениями.
     *
     * @param extraClaims  Дополнительные утверждения для включения в токен.
     * @param userDetails  Данные пользователя.
     * @return Сгенерированный JWT токен.
     */
    String generateToken(Map<String,Object> extraClaims, UserDetails userDetails);

    /**
     * Проверяет, является ли JWT токен действительным для данного пользователя.
     *
     * @param token       JWT токен.
     * @param userDetails Данные пользователя.
     * @return {@code true}, если токен действителен для данного пользователя, в противном случае - {@code false}.
     */
    boolean isTokenValid(String token, UserDetails userDetails);

    /**
     * Проверяет, истек ли срок действия JWT токена.
     *
     * @param token JWT токен.
     * @return {@code true}, если срок действия токена истек, в противном случае - {@code false}.
     */
    boolean isTokenExpired(String token);

    /**
     * Извлекает дату истечения срока действия JWT токена.
     *
     * @param token JWT токен.
     * @return Дата истечения срока действия токена.
     */
    Date extractExpiration(String token);

    /**
     * Извлекает все утверждения из JWT токена.
     *
     * @param token JWT токен.
     * @return Объект Claims, содержащий все утверждения из токена.
     */
    Claims extractAllClaims(String token);

    /**
     * Возвращает ключ для подписи JWT токена.
     *
     * @return Ключ для подписи JWT токена.
     */
    Key getSignInKey();
}
