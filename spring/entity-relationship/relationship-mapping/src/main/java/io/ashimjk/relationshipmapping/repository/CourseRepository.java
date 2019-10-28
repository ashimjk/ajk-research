package io.ashimjk.relationshipmapping.repository;

import io.ashimjk.relationshipmapping.model.Course;
import io.ashimjk.relationshipmapping.model.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@RequiredArgsConstructor
public class CourseRepository {

    private final EntityManager entityManager;

    @Transactional
    public void save() {
        Review review1 = new Review();
        review1.setRating("5");
        review1.setDescription("Awesome");

        Review review2 = new Review();
        review2.setRating("5");
        review2.setDescription("Awesome");

        entityManager.persist(review1);
        entityManager.persist(review2);

        Course course = new Course();
        course.setCourseName("Jpa");
        course.addReview(review1);
        course.addReview(review2);

        entityManager.persist(course);

        System.out.println(course);
    }
}
