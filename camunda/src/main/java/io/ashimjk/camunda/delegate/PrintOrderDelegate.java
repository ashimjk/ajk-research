package io.ashimjk.camunda.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import static io.ashimjk.camunda.ProcessConstants.VAR_AMOUNT;
import static io.ashimjk.camunda.ProcessConstants.VAR_ORDER_NAME;

@Component
public class PrintOrderDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) {

		String orderName = (String) execution.getVariable(VAR_ORDER_NAME);
		String amount = (String) execution.getVariable(VAR_AMOUNT);

		System.out.println("PrintOrderDelegate.execute");
		System.out.println("orderName = " + orderName);
		System.out.println("amount = " + amount);
	}
}
