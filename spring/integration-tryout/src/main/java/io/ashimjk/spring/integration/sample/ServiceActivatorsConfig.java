package io.ashimjk.spring.integration.sample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.config.IntegrationConverter;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

import java.nio.charset.StandardCharsets;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class ServiceActivatorsConfig {

    @Bean
    public IntegrationFlow myFlow() {
        return IntegrationFlows.from("flow3Input")
                .<Integer>handle((p, h) -> p * 2)
                .get();
    }

    @Bean
    public IntegrationFlow integerFlow() {
        return IntegrationFlows.from("input")
                .<byte[], String>transform(p -> new String(p, StandardCharsets.UTF_8))
                .handle(Integer.class, (p, h) -> p * 2)
                .get();
    }

    @Bean
    @IntegrationConverter
    public BytesToIntegerConverter bytesToIntegerConverter() {
        return new BytesToIntegerConverter();
    }

    @Bean
    public IntegrationFlow integerFlow2() {
        return IntegrationFlows.from("input")
                .handle(Integer.class, (p, h) -> p * 2)
                .get();
    }

    private static class BytesToIntegerConverter {
    }

}
