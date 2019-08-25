package io.ashimjk.keycloak;

import io.ashimjk.keycloak.resolver.PathBasedKeycloakConfigResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiBasedApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiBasedApplication.class, args);
    }

    @Bean
    public PathBasedKeycloakConfigResolver pathBasedKeycloakConfigResolver() {
        return new PathBasedKeycloakConfigResolver();
    }

}
