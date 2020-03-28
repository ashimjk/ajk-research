package io.ashimjk.spring.integration.sample;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@EnableIntegration
@IntegrationComponentScan
public class SampleConfig {

    @Bean
    public AtomicInteger integerSource() {
        return new AtomicInteger();
    }

    @Bean
    public IntegrationFlow myFlow(AtomicInteger integerSource) {
        return IntegrationFlows.from(integerSource::getAndIncrement,
                c -> c.poller(Pollers.fixedRate(100)))
                .channel("sample.gateway.channel")
                .filter((Integer p) -> p > 0)
                .transform(Object::toString)
                .log()
                .channel(MessageChannels.queue())
                .get();
    }

    @Bean
    public IntegrationFlow integerFlow() {
        return IntegrationFlows.from("input")
                .<String, Integer>transform(Integer::parseInt)
                .get();
    }

    @Bean
    public IntegrationFlow myFlow() {
        return IntegrationFlows.from("input")
                .filter("World"::equals)
                .transform("Hello "::concat)
                .handle(System.out::println)
                .get();
    }

}
