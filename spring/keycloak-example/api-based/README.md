# Keycloak Example - API Based

## Summary

Default role is `member`. All api should be accessed with its relevant role in
conjunction with `member` role.

| role    | /books | /manager |
|---------|--------|----------|
| member  |   yes  |    no    |
| manager |   yes  |    yes   |
 
## Resource
- defines which api needs to be control
- defines any scoping should be done for api

## Policy
- defines which policy should be used
- role based, user based
- role can be from realm or client specific

## Permission
- defines which type of policy should be applied to which resource
- multiple policy can be applied to single resource
- it is the connector between resource and policy

## Reference
- [ThomasVitale : spring-keycloak-tutorials](https://github.com/ThomasVitale/spring-keycloak-tutorials/blob/master/03-keycloak-spring-boot/03-keycloak-spring-boot_2/src/main/resources/application.properties)
- [ashim-jung-khadka : spring-boot-keycloak-authz](https://github.com/ashim-jung-khadka/spring-boot-keycloak-authz/blob/master/src/main/resources/application.properties)
- [enforcer-keycloak-enforcement-filter](https://github.com/keycloak/keycloak-documentation/blob/master/authorization_services/topics/enforcer-keycloak-enforcement-filter.adoc)
- [quick-start](https://github.com/keycloak/keycloak-quickstarts)