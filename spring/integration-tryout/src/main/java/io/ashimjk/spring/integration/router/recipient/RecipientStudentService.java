package io.ashimjk.spring.integration.router.recipient;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class RecipientStudentService {

    @ServiceActivator(inputChannel = "recipient.router.student.channel.1")
    public void receiveInChannel1(Message<?> message) {
        System.out.println("### student.channel.1 ###");
        System.out.println(message);
        System.out.println(message.getPayload());
    }

    @ServiceActivator(inputChannel = "recipient.router.student.channel.2")
    public void receiveInChannel2(Message<?> message) {
        System.out.println("### student.channel.2 ###");
        System.out.println(message);
        System.out.println(message.getPayload());
    }

}
