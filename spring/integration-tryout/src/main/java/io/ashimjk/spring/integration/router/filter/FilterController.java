package io.ashimjk.spring.integration.router.filter;

import io.ashimjk.spring.integration.Address;
import io.ashimjk.spring.integration.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/router/filter")
public class FilterController {

    private final FilterGateway gateway;

    @PostMapping("/student")
    public void processStudentDetails(@RequestBody Student student) {
        gateway.process(student);
    }

    @PostMapping("/address")
    public void processAddressDetails(@RequestBody Address address) {
        gateway.process(address);
    }

}
