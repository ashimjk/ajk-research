package io.ashimjk.relationshipmapping.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue
    private Long id;
    private String courseName;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime modifiedDate;

    @OneToMany(mappedBy = "course")
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
