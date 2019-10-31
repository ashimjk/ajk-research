package io.ashimjk.relationshipmapping.repository;

import io.ashimjk.relationshipmapping.model.Passport;
import io.ashimjk.relationshipmapping.model.Student;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

@Repository
@RequiredArgsConstructor
public class StudentRepository {

    private static final Logger logger = LoggerFactory.getLogger(StudentRepository.class);

    private final EntityManager entityManager;

    @Transactional
    public void save() {
        Passport passport = new Passport("E123");
        entityManager.persist(passport);

        Student student = new Student("ashim", passport);
        passport.setStudent(student);

        entityManager.persist(student);
        throw new RuntimeException("fail transaction");
    }

    @Transactional
    public void save2() {
        Passport passport = new Passport("E123");
        entityManager.persist(passport);

        Student student = new Student("ashim", passport);
        passport.setStudent(student);

        entityManager.persist(student);
    }

    @Transactional
    public void find() {
        final Student student = entityManager.find(Student.class, 4L);

        logger.info("Student Results -> {}", student);
        logger.info("Student Results -> {}", student.getPassport());
    }

    @Transactional
    public void delete() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);

        Root<Student> studentRoot = cq.from(Student.class);

        Predicate idEqual = cb.equal(studentRoot.get("id"), 20001L);

        cq.where(idEqual);

        TypedQuery<Student> studentTypedQuery = entityManager.createQuery(cq.select(studentRoot));

        logger.info("Student Results -> {}", studentTypedQuery.getResultList());

        Student student = studentTypedQuery.getSingleResult();

        Passport passport = new Passport("AS-123");

        student.setPassport(passport);

        entityManager.merge(student);
    }

}
