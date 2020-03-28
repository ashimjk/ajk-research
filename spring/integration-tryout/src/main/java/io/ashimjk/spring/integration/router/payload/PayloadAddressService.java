package io.ashimjk.spring.integration.router.payload;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class PayloadAddressService {

    @ServiceActivator(inputChannel = "payload.router.address.channel")
    public void receiveMessage(Message<?> message) {
        System.out.println("####### address.channel ###########");
        System.out.println(message);
        System.out.println("###################################");
        System.out.println(message.getPayload());
    }

}
