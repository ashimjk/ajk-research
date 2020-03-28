package io.ashimjk.spring.integration.sample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class SplittersConfig {

    @Bean
    public IntegrationFlow splitFlow() {
        TaskExecutor taskExecutor = Runnable::run;
        return IntegrationFlows.from("splitInput")
                .split(s -> s.applySequence(false).delimiters(","))
                .channel(MessageChannels.executor(taskExecutor))
                .get();
    }

}
