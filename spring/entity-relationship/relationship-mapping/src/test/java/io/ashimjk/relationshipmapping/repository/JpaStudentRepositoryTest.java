package io.ashimjk.relationshipmapping.repository;

import io.ashimjk.relationshipmapping.model.Passport;
import io.ashimjk.relationshipmapping.model.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class JpaStudentRepositoryTest {

    @Autowired
    private JpaPassportRepository jpaPassportRepository;
    @Autowired
    private JpaStudentRepository jpaStudentRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    // No Database query is being fired because repository is using first level cache for storing
    // findById is retrieving data from first level cache
    // if findById doesn't find any data then it tries to retrieve from database
    @Test
    void insertStudentTestUsingRepository() {
        Passport passport = new Passport("Z789");
        jpaPassportRepository.save(passport);

        Student student = new Student("ashim", passport);
        passport.setStudent(student);

        jpaStudentRepository.save(student);

        final Student dbStudent = jpaStudentRepository.findById(student.getId()).get();
        System.out.println(dbStudent);

        assertNotNull(dbStudent);
    }

    // No Database query is being fired because testEntityManager is also using first level cache for storing
    // findById is retrieving data from first level cache
    // if findById doesn't find any data then it tries to retrieve from database
    @Test
    void insertStudentUsingTestEntityManager() {
        Passport passport = new Passport("Z789");
        testEntityManager.persist(passport);

        Student student = new Student("ashim", passport);
        passport.setStudent(student);

        testEntityManager.persist(student);

        final Student dbStudent = testEntityManager.find(Student.class, student.getId());
        System.out.println(dbStudent);

        assertNotNull(dbStudent);
    }

    @Test
    void insertStudentUsingTestEntityManagerAndFlushIt() {
        Passport passport = new Passport("Z789");
        testEntityManager.persistAndFlush(passport);

        Student student = new Student("ashim", passport);
        passport.setStudent(student);

        testEntityManager.persistAndFlush(student);

        final Student dbStudent = testEntityManager.find(Student.class, student.getId());
        System.out.println(dbStudent);

        assertNotNull(dbStudent);
    }

    @Test
    void testLazyLoading() {
        Passport passport = new Passport("Z789");
        testEntityManager.persistAndFlush(passport);

        Student student = new Student("ashim", passport);
        passport.setStudent(student);

        testEntityManager.persistAndFlush(student);

        testEntityManager.clear();

        Student student1 = jpaStudentRepository.findById(student.getId()).get();

        System.out.println(student.hashCode());
        System.out.println(student1.hashCode()); // This code triggers the database query called lazy loading

        System.out.println(student1);
        System.out.println(student1.getPassport());
    }

}