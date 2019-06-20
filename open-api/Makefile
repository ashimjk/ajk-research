build:
	sudo rm -rf out
	docker run --rm -v ${PWD}:/local \
	openapitools/openapi-generator-cli \
	generate -i /local/${path} \
	-g spring \
	-o /local/out/java

clean:
	sudo rm -rf out

help:
	docker run --rm -v ${PWD}:/local openapitools/openapi-generator-cli config-help -g spring

install:
	mvn clean install