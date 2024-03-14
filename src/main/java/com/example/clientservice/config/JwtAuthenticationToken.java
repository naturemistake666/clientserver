package com.example.clientservice.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Класс представляющий JWT аутентификационный токен.
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private final Object principal;

    /**
     * Конструктор для создания JWT аутентификационного токена.
     *
     * @param userDetails Данные пользователя.
     * @param authorities Коллекция прав доступа пользователя.
     * @param principal   Главный объект аутентификации.
     */
    public JwtAuthenticationToken(UserDetails userDetails, Collection<? extends GrantedAuthority> authorities, Object principal) {
        super(authorities);
        this.principal = principal;
        setAuthenticated(true);
    }

    /**
     * Возвращает учетные данные (credentials).
     *
     * @return Учетные данные (credentials).
     */
    @Override
    public Object getCredentials() {
        return null;
    }

    /**
     * Возвращает главный объект аутентификации.
     *
     * @return Главный объект аутентификации.
     */
    @Override
    public Object getPrincipal() {
        return principal;
    }
}
