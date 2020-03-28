package io.ashimjk.spring.integration.sample;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface SampleGateway {

    @Gateway(requestChannel = "sample.gateway.channel")
    void sendMessage(Integer number);

}
