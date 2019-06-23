package io.ashimjk.json_merge;

import com.fasterxml.jackson.annotation.JsonMerge;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {
    private String name;
    private String dept;
    private int salary;
    private String phone;
    @JsonMerge private Address address;

    static Employee of(String name, String dept, int salary, String phone, Address address) {
        Employee e = new Employee();
        e.name = name;
        e.dept = dept;
        e.salary = salary;
        e.phone = phone;
        e.address = address;
        return e;
    }

    @Override
    public String toString() {
        return "Employee{"
            + "name='"
            + name
            + '\''
            + ", dept='"
            + dept
            + '\''
            + ", salary="
            + salary
            + ", phone='"
            + phone
            + '\''
            + ", address="
            + address
            + '}';
    }
}
