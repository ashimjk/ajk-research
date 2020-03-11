package io.ashimjk.objectmerge.model.shared;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class IdType implements Serializable {

    private static final long serialVersionUID = 4548283707786396501L;

    private String reference;
    private String name;
    private String number;
    private String document;
    private LocalDate expiryDate;

}
