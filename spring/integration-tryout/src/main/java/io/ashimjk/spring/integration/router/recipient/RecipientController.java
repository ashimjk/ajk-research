package io.ashimjk.spring.integration.router.recipient;

import io.ashimjk.spring.integration.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/router/recipient")
@RequiredArgsConstructor
public class RecipientController {

    private final RecipientGateway gateway;

    @PostMapping("/student")
    public void process(@RequestBody Student student) {
        gateway.process(student);
    }

}
