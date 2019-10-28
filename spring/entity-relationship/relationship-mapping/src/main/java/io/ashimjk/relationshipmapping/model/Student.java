package io.ashimjk.relationshipmapping.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Data
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Student {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToOne(fetch = LAZY)
    private Passport passport;

    @ManyToMany
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();

    public Student(String name, Passport passport) {
        this.name = name;
        this.passport = passport;
    }

}
