package io.ashimjk.spring.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringScheduleApplication.class, args);
    }

}
