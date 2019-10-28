package io.ashimjk.relationshipmapping.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NamedQueries(
        value = {
                @NamedQuery(name = "findAll", query = "Select c From Course c"),
                @NamedQuery(name = "findSelected", query = "select c from Course c where courseName like '%Jpa%'")
        }
)
public class Course {

    @Id
    @GeneratedValue
    private Long id;
    private String courseName;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime modifiedDate;

    @OneToMany
    @JoinColumn(name = "course_id")
    private List<Review> reviews = new ArrayList<>();

    public void addReview(Review review) {
        System.out.println("Course.addReview");
        this.reviews.add(review);
    }

    public void removeReview(Review review) {
        System.out.println("Course.removeReview");
        this.reviews.remove(review);
    }

}
