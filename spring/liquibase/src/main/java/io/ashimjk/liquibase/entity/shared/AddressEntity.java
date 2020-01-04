package io.ashimjk.liquibase.entity.shared;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class AddressEntity implements Serializable {

    private static final long serialVersionUID = -3826071631553908254L;

    private String addressName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String mapUrl;
    private String country;
    private String poBox;
    private boolean defaultAddress;

}
