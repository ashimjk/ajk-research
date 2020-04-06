package io.ashimjk.wartest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WarController {

    @GetMapping("/message")
    public String message() {
        return "Hello world";
    }

}
