package io.ashimjk.liquibase.model.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class TransactionDate implements Serializable {

    private static final long serialVersionUID = -4923983629856071031L;

    private String startingDate;
    private String reviewDate;
    private String endingDate;

}
