package io.ashimjk.liquibase.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity(name = "contact_person")
@Table(name = "contact_person")
public class ContactPersonEntity implements Serializable {

    private static final long serialVersionUID = 714091872027035973L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String cpName;

}
