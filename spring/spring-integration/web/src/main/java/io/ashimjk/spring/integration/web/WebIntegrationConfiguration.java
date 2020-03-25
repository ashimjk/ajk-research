package io.ashimjk.spring.integration.web;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public class WebIntegrationConfiguration {

    @Bean
    public MessageChannel registrationRequest() {
        return new DirectChannel();
    }

}
