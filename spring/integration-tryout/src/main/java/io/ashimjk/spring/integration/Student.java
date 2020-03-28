package io.ashimjk.spring.integration;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {

    private static final long serialVersionUID = -9054390603072930843L;

    private String name;
    private String gender;

}
