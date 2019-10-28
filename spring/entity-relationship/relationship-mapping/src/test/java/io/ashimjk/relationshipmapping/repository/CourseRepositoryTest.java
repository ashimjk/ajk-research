package io.ashimjk.relationshipmapping.repository;

import io.ashimjk.relationshipmapping.model.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    void fetchCourseAndReviewData() {
        final Course course = entityManager.find(Course.class, 3L);
        System.out.println(course);
    }

    @Test
    @Transactional
    void testNamedQuery() {
        final TypedQuery<Course> findAll = entityManager.createNamedQuery("findAll", Course.class);
        System.out.println(findAll.getResultList());
    }

    @Test
    @Transactional
    void testNamedQueryWithCondition() {
        final TypedQuery<Course> findAll = entityManager.createNamedQuery("findSelected", Course.class);
        System.out.println(findAll.getResultList());
    }

}