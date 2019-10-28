package io.ashimjk.relationshipmapping.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Review {

    @Id
    @GeneratedValue
    private Long id;
    private String rating;
    private String description;

    @ManyToOne
    private Course course;

}
