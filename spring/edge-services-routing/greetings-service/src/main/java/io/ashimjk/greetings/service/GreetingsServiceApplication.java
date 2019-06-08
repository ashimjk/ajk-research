package io.ashimjk.greetings.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@EnableDiscoveryClient
@SpringBootApplication
public class GreetingsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GreetingsServiceApplication.class, args);
    }

}

@RestController
class GreetingsRestController {

    @GetMapping("/greetings/{name}")
    public Map<String, String> greeting(@PathVariable String name) {
        return Collections.singletonMap("greeting", "Hello, " + name + "!");
    }

}
