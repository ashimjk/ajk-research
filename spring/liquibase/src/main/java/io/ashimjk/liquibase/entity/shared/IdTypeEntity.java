package io.ashimjk.liquibase.entity.shared;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Embeddable
public class IdTypeEntity implements Serializable {

    private static final long serialVersionUID = -6309100455087489598L;

    private String name;
    private String number;
    private String document;
    private LocalDate expiryDate;

}
