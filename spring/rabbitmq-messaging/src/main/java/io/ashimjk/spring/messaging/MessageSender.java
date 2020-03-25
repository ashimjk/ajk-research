package io.ashimjk.spring.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import static io.ashimjk.spring.messaging.RabbitmqMessagingApplication.EXCHANGE_NAME;
import static io.ashimjk.spring.messaging.RabbitmqMessagingApplication.ROUTING_KEY;

@Log4j2
@Service
@RequiredArgsConstructor
public class MessageSender {

    private final RabbitTemplate rabbitTemplate;
    private final AmqpTemplate amqpTemplate;

    @Scheduled(fixedDelay = 3000L)
    public void sendMessage() {
        PayloadMessage payloadMessage = new PayloadMessage("Test message", 1, false);
        amqpTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, payloadMessage);
        log.info("Message send successfully");
    }

}
