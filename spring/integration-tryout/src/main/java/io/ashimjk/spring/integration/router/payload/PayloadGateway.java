package io.ashimjk.spring.integration.router.payload;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface PayloadGateway {

    @Gateway(requestChannel = "payload.router.channel")
    <T> void processDetails(T object);

}
