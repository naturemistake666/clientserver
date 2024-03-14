package com.example.clientservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Интерфейс контроллера демонстрационного эндпоинта.
 */
public interface DemoController {
    /**
     * Обрабатывает GET-запрос на демонстрационный эндпоинт.
     *
     * @return Ответ с сообщением.
     */
    @GetMapping
    ResponseEntity<String> sayHello();
}
