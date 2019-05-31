package io.ashimjk.order.process.listener;

import io.ashimjk.order.process.ProcessConstants;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) {

        String orderName = (String) execution.getVariable(ProcessConstants.VAR_ORDER_NAME);
        String amount = (String) execution.getVariable(ProcessConstants.VAR_AMOUNT);

        System.out.println("OrderListener.notify");
        System.out.println("orderName = " + orderName);
        System.out.println("amount = " + amount);
    }

}
