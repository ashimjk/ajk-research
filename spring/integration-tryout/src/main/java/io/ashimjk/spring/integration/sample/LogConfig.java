package io.ashimjk.spring.integration.sample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.*;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.messaging.Message;

import java.util.Date;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class LogConfig {

    @Bean
    public IntegrationFlow myFlow() {
        return IntegrationFlows.from("flow3Input")
                .<Integer>handle((p, h) -> p * 2)
                .log(LoggingHandler.Level.ERROR, "test.category", m -> m.getHeaders().getId())
                .enrich(e -> e.requestChannel("enrichChannel")
                        .requestPayload(Message::getPayload)
                        .propertyFunction("date", m -> new Date()))
                .get();
    }

    @Bean
    public QueueChannelSpec myChannel() {
        return MessageChannels.queue()
                .wireTap("loggingFlow.input");
    }

    @Bean
    public IntegrationFlow loggingFlow() {
        return IntegrationFlowDefinition::log;
    }

}
