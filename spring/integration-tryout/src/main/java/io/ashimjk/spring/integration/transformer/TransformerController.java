package io.ashimjk.spring.integration.transformer;

import io.ashimjk.spring.integration.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transformer")
@RequiredArgsConstructor
public class TransformerController {

    private final TransformerGateway gateway;

    @GetMapping("/{name}")
    public String transformer(@PathVariable String name) {
        return gateway.sendMessage(name);
    }

    @PostMapping()
    public String processStudentDetails(@RequestBody Student student) {
        return gateway.processStudentDetails(student);
    }

}
