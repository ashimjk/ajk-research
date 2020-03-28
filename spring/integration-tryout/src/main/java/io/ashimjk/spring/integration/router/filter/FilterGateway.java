package io.ashimjk.spring.integration.router.filter;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface FilterGateway {

    @Gateway(requestChannel = "filter.router.channel")
    <T> void process(T object);

}
