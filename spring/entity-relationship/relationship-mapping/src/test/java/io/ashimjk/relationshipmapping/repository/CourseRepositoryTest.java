package io.ashimjk.relationshipmapping.repository;

import io.ashimjk.relationshipmapping.model.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CourseRepositoryTest {

    private static final Logger logger = LoggerFactory.getLogger(CourseRepositoryTest.class);

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

    @Test
    @Transactional
    void usingJoin() {
//        Convert following query into criteria query
//        final TypedQuery<Course> query = entityManager.createQuery("select c from Course c join c.students", Course.class);
//        logger.info("Results -> {}", query.getResultList());

        // 1. Use Criteria Builder to create a Criteria Query returning the expected result object
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Course> cq = cb.createQuery(Course.class);

        // 2. Define roots for tables which are involved in the query
        final Root<Course> partTimeEmployeeRoot = cq.from(Course.class);

        // 3. Define Predicates etc using Criteria Builder
//        partTimeEmployeeRoot.join("students");
        partTimeEmployeeRoot.join("students", JoinType.LEFT);

        // 4. Add Predicates etc to the Criteria Query

        // 5. Build the TypedQuery using the entity manager and criteria query
        final TypedQuery<Course> partTimeEmployeeTypedQuery = entityManager.createQuery(cq.select(partTimeEmployeeRoot));
        logger.info("Query Results -> {}", partTimeEmployeeTypedQuery.getResultList());
    }

}