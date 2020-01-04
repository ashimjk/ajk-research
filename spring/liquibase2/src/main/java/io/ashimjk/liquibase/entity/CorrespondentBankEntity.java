package io.ashimjk.liquibase.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity(name = "correspondent_bank")
public class CorrespondentBankEntity implements Serializable {

    private static final long serialVersionUID = -7867870521996314154L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;

}
