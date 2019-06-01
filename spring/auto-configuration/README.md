# Spring Boot Auto Configuration

## Auto-configuration
- @EnableAutoConfiguration loads /META-INF/spring.factories
- spring.factories declare @Configuration classes
- Each @Configuration is @Conditional

## @Conditional
- Defines the conditions under which @Configuration can load:
  - If a certain class is on the classpath
  - If the user hasn't declared a specific bean or has declared a specific bean
  - If a specific property is set (like application.properties)