package io.ashimjk.entitymerge.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Address implements Serializable {

    private static final long serialVersionUID = 6716882928409769243L;

    private String addressName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
    private String poBox;

}
