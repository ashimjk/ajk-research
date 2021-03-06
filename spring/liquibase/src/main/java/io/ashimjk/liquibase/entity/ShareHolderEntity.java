package io.ashimjk.liquibase.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class ShareHolderEntity implements Serializable {

    private static final long serialVersionUID = -1487758438225074075L;

    private String fullName;
    private String nationalNumber;
    private String profile;

}
