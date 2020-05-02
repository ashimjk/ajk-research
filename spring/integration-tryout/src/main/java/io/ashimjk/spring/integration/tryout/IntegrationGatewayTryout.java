package io.ashimjk.spring.integration.tryout;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.stereotype.Component;

@ComponentScan
@Configuration
@EnableIntegration
public class IntegrationGatewayTryout {

    @Bean
    public MessageChannel gatewayInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public PollableChannel gatewayOutputChannel() {
        return new QueueChannel();
    }

    @Bean
    public IntegrationFlow gatewayIntegration() {
        return IntegrationFlows.from("gatewayInputChannel")
                .log()
                .channel(gatewayOutputChannel())
                .get();
    }

    @Component
    @MessagingGateway
    public interface ControlBusGateway {

        @Gateway(requestChannel = "gatewayInputChannel")
        void send(String command);

    }
}
