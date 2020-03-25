package io.ashimjk.spring.integration.service;

import io.ashimjk.spring.integration.domain.AttendeeRegistration;
import io.ashimjk.spring.integration.service.service.RegistrationService;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class ServiceIntegrationConfiguration {

    @Bean
    public MessageChannel registrationRequest() {
        return new DirectChannel();
    }

    public MessageChannel registrationRequest2() {
        return MessageChannels.direct("registrationRequest").get();
    }

    public IntegrationFlow integrationFlow(RegistrationService registrationService) {
        return IntegrationFlows.from("registrationRequest")
                .handle(registrationService, "register")
                .get();
    }

    public IntegrationFlow integrationFlow2(RegistrationService registrationService) {
        return IntegrationFlows.from(this::registrationRequest)
                .handle(AttendeeRegistration.class, (payload, headers) -> {
                    OffsetDateTime offsetDateTime = (OffsetDateTime) headers.get("dateTime");
                    registrationService.register(offsetDateTime, payload);
                    return null;
                })
                .get();
    }

}
