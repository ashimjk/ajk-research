package io.ashimjk.spring.cloud.task.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@EnableTask
@SpringBootApplication
public class TaskServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskServiceApplication.class, args);
    }

    //@Bean
    //public TollProcessorTask tollProcessor() {
    //    return new TollProcessorTask();
    //}

    @Configuration
    public static class TollProcessorTask implements CommandLineRunner {

        @Override
        public void run(String... args) {
            if (null != args) {
                String stationId = args[0];
                String licensePlate = args[1];
                String timestamp = args[2];

                System.out.println("stationId = " + stationId);
                System.out.println("licensePlate = " + licensePlate);
                System.out.println("timestamp = " + timestamp);
            }
        }

    }

}
