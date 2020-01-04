package io.ashimjk.liquibase.entity.shared;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Embeddable
public class TransactionDateEntity implements Serializable {

    private static final long serialVersionUID = -1436066977427809410L;

    private LocalDate startingDate;
    private LocalDate reviewDate;
    private LocalDate endingDate;

}
