# Entity Relationship

## Notes
- EntityManager
- PersistenceContext (In Hibernate Terminology, Session = PersistenceContext)
- Transaction

## Summary
- When any method which is creating or updating an entity needs to be inside transaction.
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