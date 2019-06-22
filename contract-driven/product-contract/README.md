# Product Contract

## Run
```
mvn clean install
```

## How to run
- Offline
```
docker run --rm \
  -v ${PWD}:/local openapitools/openapi-generator-cli generate \
  -i local/api.yaml \
  -g spring \
  --ignore-file-override=/local/.openapi-generator-ignore \
  -o /local/out
```

- Online
```docker
docker run -d -e GENERATOR_HOST=http://localhost -p 8080:8080 openapitools/openapi-generator-online
```

## Reference
(OpenApi Config)[https://github.com/OpenAPITools/openapi-generator/blob/master/docs/customization.md]