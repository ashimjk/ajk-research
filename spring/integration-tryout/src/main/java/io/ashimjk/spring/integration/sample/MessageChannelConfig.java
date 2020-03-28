package io.ashimjk.spring.integration.sample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class MessageChannelConfig {

    //@Bean
    //public MessageChannel priorityChannel() {
    //    return MessageChannels.priority(this.mongoDbChannelMessageStore, "priorityGroup")
    //            .interceptor(wireTap())
    //            .get();
    //}

    @Bean
    public MessageChannel queueChannel() {
        return MessageChannels.queue().get();
    }

    @Bean
    public MessageChannel publishSubscribe() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    public IntegrationFlow channelFlow() {
        TaskExecutor taskExecutor = Runnable::run;
        return IntegrationFlows.from("input")
                .fixedSubscriberChannel()
                .channel("queueChannel")
                .channel(publishSubscribe())
                .channel(MessageChannels.executor("executorChannel", taskExecutor))
                .channel("output")
                .get();
    }

}
