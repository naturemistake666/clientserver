package com.example.clientservice.service.impl;


import com.example.clientservice.entity.Client;
import com.example.clientservice.repository.ClientRepository;
import com.example.clientservice.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для работы с клиентами.
 */
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    /**
     * Конструктор. Инициализирует {@code ClientServiceImpl} с указанным репозиторием клиентов.
     *
     * @param clientRepository Репозиторий клиентов для доступа к данным.
     */
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }
}
