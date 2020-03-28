package io.ashimjk.spring.integration.sample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.store.MessageGroupStore;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class AggregatorsConfig {

    @Bean
    public IntegrationFlow splitAggregateFlow() {
        TaskExecutor taskExecutor = Runnable::run;

        // expect list from message channel
        return IntegrationFlows.from("splitAggregateInput")
                .split()
                .channel(MessageChannels.executor(taskExecutor))
                .resequence()
                .aggregate()
                .aggregate(a ->
                        a.correlationStrategy(m -> m.getHeaders().get("myCorrelationKey"))
                                .releaseStrategy(g -> g.size() > 10)
                                .messageStore(messageStore()))
                .get();
    }

    private MessageGroupStore messageStore() {
        return null;
    }

}
