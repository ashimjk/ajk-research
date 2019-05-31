package io.ashimjk.order.process;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableProcessApplication
public class OrderProcessApplication {

    private final RuntimeService runtimeService;

    public OrderProcessApplication(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderProcessApplication.class, args);
    }

    @EventListener
    public void processPostDeploy(PostDeployEvent event) {
        runtimeService.startProcessInstanceByKey("loanApproval");
    }

}
