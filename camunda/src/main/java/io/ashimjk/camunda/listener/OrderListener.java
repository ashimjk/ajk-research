package io.ashimjk.camunda.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

import static io.ashimjk.camunda.ProcessConstants.VAR_AMOUNT;
import static io.ashimjk.camunda.ProcessConstants.VAR_ORDER_NAME;

@Component
public class OrderListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) {

        String orderName = (String) execution.getVariable(VAR_ORDER_NAME);
        String amount = (String) execution.getVariable(VAR_AMOUNT);

        System.out.println("OrderListener.notify");
        System.out.println("orderName = " + orderName);
        System.out.println("amount = " + amount);
    }
}
