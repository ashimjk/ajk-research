package io.ashimjk.spring.integration.router.header;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class HeaderStudentService {

    @ServiceActivator(inputChannel = "header.router.student.channel")
    public void receiveMessage(Message<?> message) {
        System.out.println("####### student.channel ###########");
        System.out.println(message);
        System.out.println("###################################");
        System.out.println(message.getPayload());
    }

}
