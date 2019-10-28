package io.ashimjk.relationshipmapping;

import io.ashimjk.relationshipmapping.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class RelationshipMappingApp implements CommandLineRunner {

    private final StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(RelationshipMappingApp.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            studentRepository.save();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        studentRepository.save2();
        studentRepository.find();
    }

}
