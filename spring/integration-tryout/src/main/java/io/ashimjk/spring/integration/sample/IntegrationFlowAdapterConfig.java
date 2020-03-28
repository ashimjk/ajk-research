package io.ashimjk.spring.integration.sample;

import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.*;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlowAdapter;
import org.springframework.integration.dsl.IntegrationFlowDefinition;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.scheduling.TriggerContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class IntegrationFlowAdapterConfig {

}

@Component
class MyFlowAdapter extends IntegrationFlowAdapter {

    private final AtomicBoolean invoked = new AtomicBoolean();

    public Date nextExecutionTime(TriggerContext triggerContext) {
        return this.invoked.getAndSet(true) ? null : new Date();
    }

    @Override
    protected IntegrationFlowDefinition<?> buildFlow() {
        return from(this, "messageSource",
                e -> e.poller(p -> p.trigger(this::nextExecutionTime)))
                .split(this)
                .transform(this)
                .aggregate(a -> a.processor(this, null))
                .enrichHeaders(Collections.singletonMap("thing1", "THING1"))
                .filter(this)
                .handle(this)
                .channel(c -> c.queue("myFlowAdapterOutput"));
    }

    public String messageSource() {
        return "T,H,I,N,G,2";
    }

    @Splitter
    public String[] split(String payload) {
        return StringUtils.commaDelimitedListToStringArray(payload);
    }

    @Transformer
    public String transform(String payload) {
        return payload.toLowerCase();
    }

    @Aggregator
    public String aggregate(List<String> payloads) {
        return payloads.stream().collect(Collectors.joining());
    }

    @Filter
    public boolean filter(@Header Optional<String> thing1) {
        return thing1.isPresent();
    }

    @ServiceActivator
    public String handle(String payload, @Header String thing1) {
        return payload + ":" + thing1;
    }

}

@Component
class MyFlow implements IntegrationFlow {

    @Override
    public void configure(IntegrationFlowDefinition<?> f) {
        f.<String, String>transform(String::toUpperCase);
    }

}
