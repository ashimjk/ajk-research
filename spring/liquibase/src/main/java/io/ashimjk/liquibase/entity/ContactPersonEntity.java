package io.ashimjk.liquibase.entity;

import io.ashimjk.liquibase.entity.shared.AddressEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity(name = "contact_person")
public class ContactPersonEntity implements Serializable {

    private static final long serialVersionUID = 714091872027035973L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String jobTitle;
    private String fullName;
    private String email;
    private String phoneNo;
    private String nationalNumber;
    private String profile;
    private Boolean primaryContact;

    @Embedded
    private AddressEntity address;

    @ElementCollection
    @OrderColumn(name = "services_order_id")
    private List<String> services = new ArrayList<>();

}
