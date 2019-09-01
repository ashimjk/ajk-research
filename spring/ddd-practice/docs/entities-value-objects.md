# Entities and Value Objects

## Introduction
- Entities
    - Have inherent identity
    - Mutable
- Value Objects
    - Don't have id field
    - Can be treated interchangeably
    - Immutable

```
SnackMachine ---> Entity
Money ---> Value Object
```

## Types of Equality
- Identifier Equality
- Reference Equality
- Structural Equality

## Entity Base Class
- Using interface provides : `Can-do Relationship`
- Using abstract class provides : `Is-a Relationship`

## 3 distinctions between Entities and Value Objects
- Reference vs structural equality
- Mutability vs immutability
- Lifespan: Value Objects should belong to Entities

## When to Write Unit Tests
- Test-First
    - Know how the code should look like

- Code-First
    - Experimenting with code

## Reference
- http://bit.ly/1XF0J6H