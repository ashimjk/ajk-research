package io.ashimjk.spring.integration.router.payload;

import io.ashimjk.spring.integration.Address;
import io.ashimjk.spring.integration.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/router/payload")
@RequiredArgsConstructor
public class PayloadController {

    private final PayloadGateway payloadGateway;

    @PostMapping("/student")
    public void processStudentDetails(@RequestBody Student student) {
        payloadGateway.processDetails(student);
    }

    @PostMapping("/address")
    public void processAddressDetails(@RequestBody Address address) {
        payloadGateway.processDetails(address);
    }

}
