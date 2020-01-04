package io.ashimjk.json_merge;

import com.fasterxml.jackson.annotation.JsonMerge;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Employee {
    private String name;
    private String dept;
    private int salary;
    private String phone;
     private List<Address> addresses;

    static Employee of(String name, String dept, int salary, String phone, List<Address> addresses) {
        Employee e = new Employee();
        e.name = name;
        e.dept = dept;
        e.salary = salary;
        e.phone = phone;
        e.addresses = addresses;
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
            + ", addresses="
            + addresses
            + '}';
    }
}
