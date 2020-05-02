package io.ashimjk.spring.integration.tryout;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;

import java.util.concurrent.Executors;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class IntegrationTryout {

    @Bean
    public MessageChannel inputSplit() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow splitAggregateFlow() {
        return IntegrationFlows
                .from("inputSplit")
                .split(splitter -> splitter.delimiters(","))
                .channel(c -> c.executor(Executors.newCachedThreadPool()))
                .<String, String>transform(String::toUpperCase)
                .resequence()
                .log(LoggingHandler.Level.WARN)
                .aggregate()
                .channel(outputSplit())
                .get();
    }

    @Bean
    public PollableChannel outputSplit() {
        return new QueueChannel();
    }

}
