package io.ashimjk.caching.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Cacheable
public class Course implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String courseName;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime modifiedDate;

    //    @JsonBackReference
//    @OneToMany(mappedBy = "course")
    @OneToMany
    @JoinColumn(name = "course_id")
    private List<Review> reviews = new ArrayList<>();

}
