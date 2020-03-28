package io.ashimjk.spring.messaging.retry;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = Employee.class)
public class Employee {

    private String empName;
    private String empId;
    private int salary;

}
