package io.ashimjk.spring.messaging.retry;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class RabbitMQConsumer {

    @RabbitListener(queues = "retry.queue")
    public void receivedMessage(Employee employee) throws InvalidSalaryException {
        log.info("Received Message From RabbitMQ: " + employee);

        if (employee.getSalary() < 0) {
            throw new InvalidSalaryException();
        }
    }

}
