package io.ashimjk.tddworkout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class TddWorkoutApplication {

    public static void main(String[] args) {
        SpringApplication.run(TddWorkoutApplication.class, args);
    }

}
