# Entity Relationship

## Notes
- EntityManager
- PersistenceContext (In Hibernate Terminology, Session = PersistenceContext)
- Transaction

## Four Types of  Inheritance in JPA
- SINGLE_TABLE
    - create a single table with one more column called employeeType (default is dType)
```java
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "employeeType")
```
- TABLE_PER_CLASS
    - creates individual table for all concrete class
    - loosely type classes i.e. data are extracted using abstract class
```java
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
```
- JOINED
    - creates individual table for all concrete class with one more table containing all the abstract class fields 
```java
@Inheritance(strategy = InheritanceType.JOINED)
```

- MappedSuperclass
    - behaviour are same as TABLE_PER_CLASS with one difference
    - strongly typed classes i.e. data are extracted using concrete class
```java
@MappedSuperclass
```

## Criteria Query
Building sql query using Java APIs
```java
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;

@Repository
class PartTimeRepository {

    private final EntityManager entityManager;

    void usingCriteriaQuery() {
        // Convert following query into criteria query
        // final TypedQuery<PartTimeEmployee> query = entityManager.createQuery("select e from PartTimeEmployee e where e.name like '%ashish%'", PartTimeEmployee.class);
        
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
```


## Summary
- When any changes that are going to be made on the database then that particular method should be inside transaction.
    - Like create, update, delete
- But find or get method are already inside a transaction, so its not necessary for them.
    - It opens when request and close transaction immediately
- At the startup of transaction, PersistenceContext gets created.
    - PersistenceContext resides only inside given transaction.
    - PersistenceContext acts as a temporary data storage for Entity.
    - It tracks all the changes made on Entity.
- Al the end of transaction, PersistenceContext gets deleted.
    - When transaction is closed, all the changes made on PersistenceContext
    will be persisted into database.
- Here is EntityManager provides different APIs to communicate with the PersistenceContext.
    - Like find, persist, merge, delete etc...
    
- In Hibernate Terminology:
    - Session = PersistenceContext
    - SessionFactory - Acts as a bridge for getting EntityManager
    
## Caveats
- When using JPA Auto Id Generation Strategy, if any exception is thrown then Id will get lost. 