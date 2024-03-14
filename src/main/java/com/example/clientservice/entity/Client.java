package com.example.clientservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Класс, представляющий сущность клиента.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table(name = "clients", schema = "client")
public class Client implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String pass;
    @Enumerated(EnumType.STRING)
    private Role roles;

    /**
     * Возвращает коллекцию прав доступа пользователя.
     *
     * @return Коллекция объектов {@code GrantedAuthority}, представляющих права доступа пользователя.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles.name()));
    }

    /**
     * Возвращает пароль пользователя.
     *
     * @return Пароль пользователя.
     */
    @Override
    public String getPassword() {
        return pass;
    }

    /**
     * Возвращает имя пользователя.
     *
     * @return Имя пользователя.
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Проверяет, истек ли срок действия учетной записи пользователя.
     *
     * @return {@code true}, если срок действия учетной записи не истек, в противном случае - {@code false}.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Проверяет, заблокирована ли учетная запись пользователя.
     *
     * @return {@code true}, если учетная запись не заблокирована, в противном случае - {@code false}.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Проверяет, истек ли срок действия учетных данных пользователя.
     *
     * @return {@code true}, если срок действия учетных данных не истек, в противном случае - {@code false}.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Проверяет, активна ли учетная запись пользователя.
     *
     * @return {@code true}, если учетная запись активна, в противном случае - {@code false}.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
