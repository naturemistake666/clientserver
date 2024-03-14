package com.example.clientservice.service;

import com.example.clientservice.entity.Client;


import java.util.List;

/**
 * Интерфейс, определяющий контракт для сервиса работы с клиентами.
 */
public interface ClientService {

    /**
     * Возвращает список всех клиентов в системе.
     *
     * @return Список объектов {@code Client}, содержащий всех клиентов в системе.
     */
    List<Client> findAllClients();
}
