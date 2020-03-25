package io.ashimjk.spring.messaging.topicExchange;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static io.ashimjk.spring.messaging.topicExchange.TopicExchangeConfig.ROUTING_KEY_ADMIN;
import static io.ashimjk.spring.messaging.topicExchange.TopicExchangeConfig.TOPIC_EXCHANGE;

@RestController
@RequiredArgsConstructor
public class TopicExchangeController {

    private final AmqpTemplate amqpTemplate;

    @GetMapping("/topic-exchange")
    public String topicExchange() {
        String message = "Message sent to topic exchange";
        amqpTemplate.convertAndSend(TOPIC_EXCHANGE, ROUTING_KEY_ADMIN, message);

        return message;
    }

}
