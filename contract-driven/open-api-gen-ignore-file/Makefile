build:
	rm -rf target && \
    docker run --rm \
      --env JAVA_POST_PROCESS_FILE='/usr/local/bin/clang-format -i' \
      -v ${PWD}:/local openapitools/openapi-generator-cli generate \
      -i local/api.yaml \
      -g spring \
      --ignore-file-override=/local/.openapi-generator-ignore \
      -o /local/target;

clean:
	rm -rf target
	rm -rf target
help:
	docker run --rm -v ${PWD}:/local openapitools/openapi-generator-cli config-help -g spring

install:
	mvn clean install