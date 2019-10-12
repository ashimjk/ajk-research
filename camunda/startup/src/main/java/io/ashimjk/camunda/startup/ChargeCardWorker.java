package io.ashimjk.camunda.startup;

import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;

import java.util.logging.Logger;

public class ChargeCardWorker {

    private static Logger logger = Logger.getLogger(ChargeCardWorker.class.getName());

    public static void main(String[] args) {

        ExternalTaskClient externalTaskClient = ExternalTaskClient.create()
                .baseUrl("http://localhost:8080/engine-rest")
                .asyncResponseTimeout(10000) // long polling timeout
                .build();

        // subscribe to an external task topic as specified in the process
        externalTaskClient.subscribe("charge-card")
                .lockDuration(1000) // the default lock duration is 20 seconds, but you can override this
                .handler((ExternalTask externalTask, ExternalTaskService externalTaskService) -> {
                    // Business logic

                    // Get a process variable
                    String item = externalTask.getVariable("item");
                    Long amount = externalTask.getVariable("amount");

                    logger.info(String.format("Charging credit card with an amount of '%s'€ for the item '%s}'...", amount, item));

                    // Complete the task
                    externalTaskService.complete(externalTask);
                })
                .open();
    }


}
