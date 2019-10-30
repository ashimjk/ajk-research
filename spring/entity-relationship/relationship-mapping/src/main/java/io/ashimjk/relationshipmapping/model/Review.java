package io.ashimjk.relationshipmapping.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
@ToString(exclude = "course")
@EqualsAndHashCode(exclude = "course")
public class Review {

    @Id
    @GeneratedValue
    private Long id;
    private String rating;
    private String description;

    @ManyToOne
    private Course course;

}
