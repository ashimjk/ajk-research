# TDD Workout
***RED - GREEN - REFACTOR***

## Test Case Pattern
- *Arrange, Act, Assert*
- *Given, When, Then*

## TDD Approaches
It is sort of like pyramid with three layers.
     
- **Top-down approach**
    - Integration Test
    - Controller Test
    - Service Test
    - Repository Test
    - Any other test that needs to be done like Security, AOP, Caching.
- **Bottom-up approach**
    - Repository Test
    - Service Test
    - Business Implementation Test
    - Controller Test
    - Integration Test
    - Any other test that needs to be done like Security, AOP, Caching.

## Keynotes
- ***Slice Test***
    - Writing test for a single component in complete isolation.
    For Example : In Controller Test, we are only testing controller not service or repository.

- ***Boundary Test / Control Test / Not Part of Flow Test***
    - It is highly recommended to write these test at unit level. Because it will be
    easier to write the test as well as debug it.
    
        Like we have written data not found exception handling test in controller and service.

- ***Black Box Testing / Behaviour Testing***
    - It is not always possible to go for behaviour testing at unit level. Because we need to verify
        our data at state level. But, know it before you do it.

    - Always try to write your test in isolation and try to use black box testing. The less information you have in test, is better. It removes tight coupling with your dependencies.

    - Think of our mock object as a tight coupling dependencies. Try to minimize, when...thenReturn format. It makes our test case to know more about our dependencies.

        For Example : In our LgDraftControllerTest, LgDraftServiceTest, we are using when...thenReturnFormat. This makes our test case know more about our dependency implementation.
        It is acceptable in this scenario, but look for this kind of behaviour.

- ***Design Pattern***
    - SOLID Principle
    - Tell Don't Ask Principle
    - Law of Demeter
    - Domain Driven Development
    - Backward Programming
    - Demorgan's Law

## Pyramid Test
It contains three layers:
- **TOP** : Integration
- **MIDDLE** : Unit Tests + Spring
- **BOTTOM** : Unit Tests

### Integration Tests Annotation
*It includes:*
- Controller + Service + Repository
- Security
- AOP
- Scheduler etc...

*Helper:*
- SpringRunner
- @SpringBootTest
- TestRestTemplate

### Unit Tests + Spring
*It includes:*
- Repository
- Security
- Scheduler
- Caching etc...

*Helper:*
- SpringRunner
- @SpringBootTest
- @DataJpaTest
- @DataMongoTest
- @WebMvcTest
- @WebFluxTest
- @MockBean
- TestEntityManager
- MockMvc

### Unit Tests
*It includes:*
- Service
- Business Logic

*Helper:*
- MockitoJUnitRunner
- @Mock
- @InjectMocks

## Annotations

| Annotation                          | Description
|-------------------------------      |--------------
|`@RunWith(SpringRunner.class)`       | `@SpringJUnit4ClassRunner` alias; add for junit test support
|   	                              |
|`@SpringBootTest`  	              | Bootstrap test with SpringBoot support, load application.properties;
|  	                                  | Specify random or specific port to start app; TestRestTemplate bean made available;
|                                     |
|`@WebMvcTest`   	                  | Use in combination with SpringRunner to load context relevant spring mvc components
|                                     |
|`@RunWith(MockitoJUnitRunner.class)` | Initializes mocks so no need to initMocks(this); automatic validation of framework usage
|                                     |
|`@DataJpaTest`   	                  | Loads jpa relevant config; uses in-memory db by default, override with `@AutoConfigureTestDatabase`
|   	                              |
|`@AutoConfigureTestDatabase`   	  | If you do not want to use auto-configured test database, use this to configure a test db
|  	                                  |
|`@MockBean`                          | Use with SpringRunner class to mock components in test
|                                     |
|`@Mock`    	                      | Similar to `@MockBean` but without spring support; use with MockitoJUnitRunner
|  	                                  |
|`@AutoConfigureMockMvc`              | More control of mock-mvc, disable spring security bits etc
|                                     |
|`@WebFluxTest`                       | Use in combination with SpringRunner to load context relevant spring WebFlux components
|                                     |
|`@DataMongoTest`                     | Use in combination with SpringRunner for testing MongoDB components; uses in-memory MongoDB by default
