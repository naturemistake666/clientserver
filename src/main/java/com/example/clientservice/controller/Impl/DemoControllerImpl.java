package com.example.clientservice.controller.Impl;

import com.example.clientservice.controller.DemoController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Класс контроллера демонстрационного эндпоинта.
 */
@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoControllerImpl implements DemoController {
    @Override
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello from secured endpoint");
    }

}
