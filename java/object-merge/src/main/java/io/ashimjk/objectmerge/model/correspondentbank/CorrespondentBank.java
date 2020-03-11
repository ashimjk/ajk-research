package io.ashimjk.objectmerge.model.correspondentbank;

import io.ashimjk.objectmerge.model.shared.Address;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(of = "reference")
public class CorrespondentBank implements Serializable {

    public static final String SWIFT_CODE_PATTERN = "^[A-Z]{6}[A-Z0-9]{2}([A-Z0-9]{3})?$";
    private static final long serialVersionUID = 213942640908917631L;

    private Long id;
    private String reference;
    private String name;
    private String swiftCode;
    private Address address;
    private List<String> services = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();

}
