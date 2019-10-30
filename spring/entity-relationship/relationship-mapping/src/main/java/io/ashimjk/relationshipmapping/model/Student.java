package io.ashimjk.relationshipmapping.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Data
@Entity
@ToString(exclude = "courses")
@EqualsAndHashCode(exclude = "courses")
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
