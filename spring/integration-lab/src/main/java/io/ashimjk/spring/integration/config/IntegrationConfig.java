package io.ashimjk.spring.integration.config;

import io.ashimjk.spring.integration.domain.Beneficiary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.json.JsonToObjectTransformer;

import java.io.File;
import java.time.LocalDateTime;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class IntegrationConfig {

    @Bean
    public IntegrationFlow flowIntegrationFlow(@Value("${input-dir:classpath:data}") File in) {
        return IntegrationFlows.from(
                Files.inboundAdapter(in).autoCreateDirectory(true)
                        .patternFilter("*.json"),
                polling -> polling.poller(Pollers.fixedRate(3000))
        )
                .transform(new JsonToObjectTransformer(Beneficiary.class))
                .enrich(enricherSpec -> {
                    enricherSpec.requestPayload(message -> {
                        Beneficiary beneficiary = (Beneficiary) message.getPayload();
                        beneficiary.setTimestamp(LocalDateTime.now());
                        return message;
                    });
                })
                .log()
                .routeToRecipients(r -> r
                        .applySequence(true)
                        .ignoreSendFailures(true)
                        .recipient("fileOutputChannel")
                        .recipient("amqpOutputChannel")
                        .recipient("dataPersist")
                )
                .get();
    }

}
