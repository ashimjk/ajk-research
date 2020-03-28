package io.ashimjk.spring.integration.router.header;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface HeaderGateway {

    @Gateway(requestChannel = "header.router.channel")
    <T> void processDetails(T object);

}
