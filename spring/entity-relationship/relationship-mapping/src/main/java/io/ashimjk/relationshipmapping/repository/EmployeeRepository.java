package io.ashimjk.relationshipmapping.repository;

import groovy.util.logging.Slf4j;
import io.ashimjk.relationshipmapping.model.FullTimeEmployee;
import io.ashimjk.relationshipmapping.model.PartTimeEmployee;
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
import java.math.BigDecimal;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EmployeeRepository {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeRepository.class);

    private final EntityManager entityManager;

    @Transactional
    public void save() {
        FullTimeEmployee fullTimeEmployee = new FullTimeEmployee("ashim", new BigDecimal("1200"));
        PartTimeEmployee partTimeEmployee = new PartTimeEmployee("ashish", new BigDecimal("150"));

        entityManager.persist(fullTimeEmployee);
        entityManager.persist(partTimeEmployee);
    }

    public void getAll() {
        // Doesn't not work with MappedSuperclass because Employee is not an entity
//        final TypedQuery<Employee> query = entityManager.createQuery("select e from Employee e order by e.id desc", Employee.class);
//        logger.info("Results -> {}", query.getResultList());
        getFullTimeAll();
        getPartTimeAll();
        getPartTimeUsingCriteriaQuery();
    }

    private void getFullTimeAll() {
        final TypedQuery<FullTimeEmployee> query = entityManager.createQuery("select e from FullTimeEmployee e order by e.id desc", FullTimeEmployee.class);
        logger.info("Results -> {}", query.getResultList());
    }

    private void getPartTimeAll() {
        final TypedQuery<PartTimeEmployee> query = entityManager.createQuery("select e from PartTimeEmployee e where e.name like '%ashish%'", PartTimeEmployee.class);
        logger.info("Results -> {}", query.getResultList());
    }

    private void getPartTimeUsingCriteriaQuery() {
//        Convert following query into criteria query
//        final TypedQuery<PartTimeEmployee> query = entityManager.createQuery("select e from PartTimeEmployee e where e.name like '%ashish%'", PartTimeEmployee.class);
//        logger.info("Results -> {}", query.getResultList());

        // 1. Use Criteria Builder to create a Criteria Query returning the expected result object
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<PartTimeEmployee> cq = cb.createQuery(PartTimeEmployee.class);

        // 2. Define roots for tables which are involved in the query
        final Root<PartTimeEmployee> partTimeEmployeeRoot = cq.from(PartTimeEmployee.class);

        // 3. Define Predicates etc using Criteria Builder
        final Predicate likeAshish = cb.like(partTimeEmployeeRoot.get("name"), "%ashish%");

        // 4. Add Predicates etc to the Criteria Query
        cq.where(likeAshish);

        // 5. Build the TypedQuery using the entity manager and criteria query
        final TypedQuery<PartTimeEmployee> partTimeEmployeeTypedQuery = entityManager.createQuery(cq.select(partTimeEmployeeRoot));
        logger.info("Query Results -> {}", partTimeEmployeeTypedQuery.getResultList());
    }

}
