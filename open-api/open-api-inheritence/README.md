# Open API Inheritance and Polymorphism

## Model Composition
In our API, we may have model schemas that share common properties.
Instead of describing these properties for each schema repeatedly, we can describe the schemas
as a composition of the common property set and schema-specific properties.
In OpenAPI version 3, we do this with the allOf keyword:

## Polymorphism
In our API, we can have request and responses that can be described by
several alternative schemas.
In OpenAPI 3.0, to describe such a model, we can use the oneOf or anyOf keywords:

## Discriminator
To help API consumers detect the object type, we can add the discriminator/propertyName
keyword to model definitions.

The discriminator keyword can be used by various API consumers. One possible example are
code generation tools: they can use discriminator to generate program statements that
typecast request data to appropriate object type based on the discriminator property value.

## Run
```docker
docker run --rm -v ${PWD}:/local openapitools/openapi-generator-cli generate -i /local/openapi.yaml -g java -o /local/out/java
```
## Reference
[Spring Generator](https://openapi-generator.tech/docs/generators/spring)
[Maven Plugin](https://openapi-generator.tech/docs/plugins)
[Usage](https://openapi-generator.tech/docs/usage)
[OpenAPI Specification](http://spec.openapis.org/oas/v3.0.2#toc)