package com.example.clientservice.repository;

import com.example.clientservice.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для доступа к данным клиентов.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    /**
     * Поиск клиента по адресу электронной почты.
     *
     * @param email Адрес электронной почты клиента.
     * @return Объект {@code Optional<Client>}, содержащий найденного клиента (если найден).
     */
    @Query("SELECT c from Client c where c.email = :email")
    Optional<Client> findByEmail(@Param("email")String email);
}
