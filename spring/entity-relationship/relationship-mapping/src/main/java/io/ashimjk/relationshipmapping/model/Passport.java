package io.ashimjk.relationshipmapping.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Data
@Entity
@ToString(exclude = "student")
@NoArgsConstructor(access = PROTECTED)
public class Passport {

    @Id
    @GeneratedValue
    private Long id;
    private String passportNumber;

    @OneToOne(fetch = LAZY, mappedBy = "passport")
    private Student student;

    public Passport(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Passport(String passportNumber, Student student) {
        this.passportNumber = passportNumber;
        this.student = student;
    }

}
