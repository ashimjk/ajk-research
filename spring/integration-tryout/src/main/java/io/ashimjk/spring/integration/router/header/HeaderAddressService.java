package io.ashimjk.spring.integration.router.header;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class HeaderAddressService {

    @ServiceActivator(inputChannel = "header.router.address.channel")
    public void receiveMessage(Message<?> message) {
        System.out.println("####### address.channel ###########");
        System.out.println(message);
        System.out.println("###################################");
        System.out.println(message.getPayload());
    }

}
