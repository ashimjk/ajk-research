package io.ashimjk.relationshipmapping.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import static lombok.AccessLevel.PROTECTED;

@Data
//@Entity

//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "employeeType")

//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

//@Inheritance(strategy = InheritanceType.JOINED)

@MappedSuperclass

@NoArgsConstructor(access = PROTECTED)
abstract class Employee {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    Employee(String name) {
        this.name = name;
    }

}
