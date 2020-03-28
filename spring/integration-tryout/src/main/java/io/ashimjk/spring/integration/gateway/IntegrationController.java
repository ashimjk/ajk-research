package io.ashimjk.spring.integration.gateway;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class IntegrationController {

    private final IntegrationGateway gateway;

    @GetMapping("gateway/{name}")
    public String gateway(@PathVariable String name) {
        return gateway.sendMessage(name);
    }

}
