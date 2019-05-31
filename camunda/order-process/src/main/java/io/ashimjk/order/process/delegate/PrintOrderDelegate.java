package io.ashimjk.order.process.delegate;

import io.ashimjk.order.process.ProcessConstants;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class PrintOrderDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {

        String orderName = (String) execution.getVariable(ProcessConstants.VAR_ORDER_NAME);
        String amount = (String) execution.getVariable(ProcessConstants.VAR_AMOUNT);

        System.out.println("PrintOrderDelegate.execute");
        System.out.println("orderName = " + orderName);
        System.out.println("amount = " + amount);
    }

}
