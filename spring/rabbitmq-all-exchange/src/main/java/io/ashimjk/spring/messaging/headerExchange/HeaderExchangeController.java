package io.ashimjk.spring.messaging.headerExchange;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static io.ashimjk.spring.messaging.headerExchange.HeaderExchangeConfig.HEADER_EXCHANGE;
import static io.ashimjk.spring.messaging.headerExchange.HeaderExchangeConfig.ROUTING_KEY_ADMIN;

@RestController
@RequiredArgsConstructor
public class HeaderExchangeController {

    private final AmqpTemplate amqpTemplate;

    @GetMapping("/header-exchange")
    public String headerExchange() {
        String messageData = "Message sent to header exchange";

        //HashMap<String, Object> headers = new HashMap<>();
        //headers.put("department", ROUTING_KEY_ADMIN);
        //
        //MessageHeaders messageHeaders = new MessageHeaders(headers);
        //SimpleMessageConverter converter = new SimpleMessageConverter();
        //Message message = converter.toMessage(messageData, messageHeaders);
        //
        //amqpTemplate.send(HEADER_EXCHANGE, "", message);

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("department", ROUTING_KEY_ADMIN);
        MessageConverter messageConverter = new SimpleMessageConverter();
        Message message = messageConverter.toMessage(messageData, messageProperties);
        amqpTemplate.send(HEADER_EXCHANGE, "", message);

        return messageData;
    }

}
