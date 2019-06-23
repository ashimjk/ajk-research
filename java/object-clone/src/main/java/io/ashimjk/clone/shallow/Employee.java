package io.ashimjk.clone.shallow;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class Employee implements Cloneable {

    private int id;
    private String name;
    private Department department;

    @SneakyThrows
    static Employee clone(Employee e) {
        return (Employee) e.clone();
    }

}
