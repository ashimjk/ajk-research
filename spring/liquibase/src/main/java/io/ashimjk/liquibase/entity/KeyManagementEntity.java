package io.ashimjk.liquibase.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class KeyManagementEntity implements Serializable {

    private static final long serialVersionUID = 6331358130694851445L;

    private String fullName;
    private String jobTitle;
    private String nationalNumber;
    private String profile;

}
