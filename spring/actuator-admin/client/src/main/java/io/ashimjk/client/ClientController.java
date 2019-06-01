package io.ashimjk.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ClientController {

    @GetMapping
    public String welcome() {
        return "Welcome to Spring Boot Admin Client Application!";
    }

}
