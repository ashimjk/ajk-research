package io.ashimjk.order.process.controller;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static io.ashimjk.order.process.ProcessConstants.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private RuntimeService runtimeService;
    private TaskService taskService;

    public OrderController(RuntimeService runtimeService, TaskService taskService) {
        this.runtimeService = runtimeService;
        this.taskService = taskService;
    }

    @GetMapping
    public void startOrderProcess(@RequestParam String orderName,
                                  @RequestParam String amount) {

        runtimeService.startProcessInstanceByKey(ORDER_PROCESS,
                Variables.createVariables()
                        .putValue(VAR_ORDER_NAME, orderName)
                        .putValue(VAR_AMOUNT, amount));

        List<Task> taskList = taskService.createTaskQuery().list();

        taskList.forEach(this::print);

        System.out.println("-------------------------------------------------");
        System.out.println("OrderController.startOrderProcess");
        System.out.println("orderName = " + orderName);
        System.out.println("amount = " + amount);
    }

    private void print(Task task) {
        System.out.println("Id : " + task.getId());
        System.out.println("Name : " + task.getName());
        System.out.println("Description : " + task.getDescription());
        System.out.println("Priority : " + task.getPriority());
        System.out.println("Owner : " + task.getOwner());
        System.out.println("Assignee : " + task.getAssignee());
        System.out.println("DelegationState : " + task.getDelegationState());
        System.out.println("ProcessInstanceId : " + task.getProcessInstanceId());
        System.out.println("ExecutionId : " + task.getExecutionId());
        System.out.println("ProcessDefinitionId : " + task.getProcessDefinitionId());
        System.out.println("CaseInstanceId : " + task.getCaseInstanceId());
        System.out.println("CaseExecutionId : " + task.getCaseExecutionId());
        System.out.println("CaseDefinitionId : " + task.getCaseDefinitionId());
        System.out.println("CreateTime : " + task.getCreateTime());
        System.out.println("TaskDefinitionKey : " + task.getTaskDefinitionKey());
        System.out.println("DueDate : " + task.getDueDate());
        System.out.println("FollowUpDate : " + task.getFollowUpDate());
        System.out.println("ParentTaskId : " + task.getParentTaskId());
        System.out.println("TenantId : " + task.getTenantId());
    }

    @GetMapping("/complete")
    public void completeTask(@RequestParam String taskId) {
        taskService.complete(taskId);
    }

}
