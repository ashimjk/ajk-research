package io.ashimjk.spring.messaging.retry;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PublisherController {

    private final AmqpTemplate amqpTemplate;

    @GetMapping(value = "/producer")
    public String producer() {
        Employee emp = new Employee();
        emp.setEmpId(UUID.randomUUID().toString());
        emp.setEmpName("ashim");
        emp.setSalary(-100);

        amqpTemplate.convertAndSend(RabbitMQConfig.RETRY_EXCHANGE, RabbitMQConfig.RETRY_ROUTING_KEY, emp);
        return "Message sent to the RabbitMQ Successfully";
    }

}
