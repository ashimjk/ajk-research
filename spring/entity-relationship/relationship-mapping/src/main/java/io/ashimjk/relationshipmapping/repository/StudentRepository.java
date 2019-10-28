package io.ashimjk.relationshipmapping.repository;

import io.ashimjk.relationshipmapping.model.Passport;
import io.ashimjk.relationshipmapping.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@RequiredArgsConstructor
public class StudentRepository {

    private final EntityManager entityManager;

    @Transactional
    public void save() {
        Passport passport = new Passport("E123");
        entityManager.persist(passport);

        Student student = new Student("ashim", passport);

        entityManager.persist(student);

    }

    @Transactional
    public void find() {
        final Student student = entityManager.find(Student.class, 2L);

        System.out.println(student);
        System.out.println(student.getPassport());
    }

}
