package io.ashimjk.spring.messaging.directExchange;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static io.ashimjk.spring.messaging.directExchange.DirectExchangeConfig.DIRECT_EXCHANGE;
import static io.ashimjk.spring.messaging.directExchange.DirectExchangeConfig.ROUTING_KEY_ADMIN;

@RestController
@RequiredArgsConstructor
public class DirectExchangeController {

    private final AmqpTemplate amqpTemplate;

    @GetMapping("/direct-exchange")
    public String directExchange() {
        String message = "Message sent to direct exchange";
        amqpTemplate.convertAndSend(DIRECT_EXCHANGE, ROUTING_KEY_ADMIN, message);

        return message;
    }

}
