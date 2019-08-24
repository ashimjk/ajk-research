# Keycloak Example - API Based

## Requirement
- create default role called `user` for keycloak security context to be loaded.
- this role should be assigned to every user who is going to access the protected resource.

## Access Control
### Resource
- Book Resource (Scope Based Permission)
    - is under `member-scope`
    - has `Member Scope Permission`
    - uris `/books`
 
- Manage Resource (Resouce Based Permission)
    - `no scope`
    - has `Manage Resource Permission`
    - uris `/manager`

### Policy
- Only Member Policy
    - has realm role `member`
    - has `Member Scope Permission`

- Only Manager Policy
    - has realm role `manager`
    - has `Manage Resource Permission`

### Permission
- Member Scope Permission (Scope Based)
    - has no resource
    - has `member_scope`
    - has `Only Member Policy`

- Manage Resource Permission (Resource Based)
    - has `Manage Resource`
    - has `Only Manager Policy`


## Resource based permission
It is directly attach with the resource.

## Scope based permission
It best suitable when dynamic permission needs to be applied.
If permission is not tied to the resource, then it works dynamically

- If resource not defined in permission
    - No resource will be associated with it
    - As soon as, resource get associated with scope like `member_scope` then permission
    will be automatically attach to those resource.
- If resource defined in permission
    - If you remove scope from resource like `member_scope`, but still permission will
    be attach because of tightly coupling with resource

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