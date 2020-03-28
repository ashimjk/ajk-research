package io.ashimjk.spring.integration;

import lombok.Data;

import java.io.Serializable;

@Data
public class Address implements Serializable {

    private static final long serialVersionUID = -8203342702554240967L;

    private String city;
    private String country;

}
