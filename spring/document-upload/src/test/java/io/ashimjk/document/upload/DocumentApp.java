package io.ashimjk.document.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(considerNestedRepositories = true)
public class DocumentApp {

    public static void main(String[] args) {
        SpringApplication.run(DocumentApp.class, args);
    }

}
