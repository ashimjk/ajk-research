package io.ashimjk.caching.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
@ToString(exclude = "course")
@EqualsAndHashCode(exclude = "course")
class Review implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String rating;
    private String description;

//    @JsonManagedReference
//    @ManyToOne
//    private Course course;

}
