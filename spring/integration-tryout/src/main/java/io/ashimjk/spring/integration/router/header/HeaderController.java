package io.ashimjk.spring.integration.router.header;

import io.ashimjk.spring.integration.Address;
import io.ashimjk.spring.integration.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/router/header")
@RequiredArgsConstructor
public class HeaderController {

    private final HeaderGateway gateway;

    @PostMapping("/student")
    public void processStudentDetails(@RequestBody Student student) {
        gateway.processDetails(student);
    }

    @PostMapping("/address")
    public void processAddressDetails(@RequestBody Address address) {
        gateway.processDetails(address);
    }

}
