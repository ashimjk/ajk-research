# Camunda

## Runner
```
mvn clean spring-boot:run
```

> While running program, by default it will load all the available bpmn in the project.
>- orderProcess.bpmn
>- loanApproval.bpmn

## Exposed API
```
curl http://localhost:8080/order?orderName=camunda&amount=5000
curl http://localhost:8080/order/complete?taskId=60766333-65e8-11e9-85f3-2acf81df30c6
```

## Camunda Dashboard
### TaskList
- http://localhost:8080/app/tasklist

### Cockpit
- http://localhost:8080/app/cockpit

### Admin
- http://localhost:8080/app/admin

## Reference
- [Camunda BPMN 2.0 Reference](https://docs.camunda.org/manual/7.10/reference/bpmn20/)