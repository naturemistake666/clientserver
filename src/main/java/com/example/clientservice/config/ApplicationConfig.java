package com.example.clientservice.config;

import com.example.clientservice.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Конфигурация приложения.
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private static final String STRING_ERROR = "Пользователь не найден";

    private final ClientRepository clientRepository;

    /**
     * Создает сервис пользователей.
     *
     * @return Сервис пользователей.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> clientRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(STRING_ERROR));
    }

    /**
     * Создает провайдер аутентификации.
     *
     * @return Провайдер аутентификации.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Создает менеджер аутентификации.
     *
     * @param config Конфигурация аутентификации.
     * @return Менеджер аутентификации.
     * @throws Exception Если возникает ошибка при создании менеджера аутентификации.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Создает кодировщик паролей.
     *
     * @return Кодировщик паролей.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
