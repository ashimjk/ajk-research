package io.ashimjk.spring.integration.router.recipient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

import java.util.concurrent.Executor;

@Configuration
public class ExecutorConfig {

    @Bean
    public IntegrationFlow startJobTask() {
        return IntegrationFlows.from("TaskRoutingChannel")
                .handle("jobService", "executeTasks")
                .routeToRecipients(r -> r
                        .recipient("testTaskChannel")
                        .recipient("test2TaskChannel"))
                .get();
    }

    @Bean
    ExecutorChannel testTaskChannel() {
        return new ExecutorChannel(this.getAsyncExecutor());
    }

    @Bean
    ExecutorChannel test2TaskChannel() {
        return new ExecutorChannel(this.getAsyncExecutor());
    }

    @Bean
    public Executor getAsyncExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

    @Bean
    public IntegrationFlow testTaskFlow() {
        return IntegrationFlows.from("testTaskChannel")
                .handle("testTaskService", "executeAsync")
                .get();
    }

    @Bean
    public IntegrationFlow test2TaskFlow() {
        return IntegrationFlows.from("test2TaskChannel")
                .handle("test2TaskService", "executeAsync")
                .get();
    }

}
