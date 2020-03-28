package io.ashimjk.spring.messaging.retry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RetryPublisherApplication {

    public static void main(String[] args) {
        SpringApplication.run(RetryPublisherApplication.class, args);
    }

}
