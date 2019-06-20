package io.ashimjk.cucumber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class CucumberApplication {

    public static void main(String[] args) {
        SpringApplication.run(CucumberApplication.class, args);
    }

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("/message")
    public String message(@RequestParam String msg) {
        return msg;
    }

}
