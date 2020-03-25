package io.ashimjk.spring.messaging.fanoutExchange;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static io.ashimjk.spring.messaging.fanoutExchange.FanoutExchangeConfig.FANOUT_EXCHANGE;

@RestController
@RequiredArgsConstructor
public class FanoutExchangeController {

    private final AmqpTemplate amqpTemplate;

    @GetMapping("/fanout-exchange")
    public String fanoutExchange() {
        String message = "Message sent to fanout exchange";
        amqpTemplate.convertAndSend(FANOUT_EXCHANGE, "", message);

        return message;
    }

}
