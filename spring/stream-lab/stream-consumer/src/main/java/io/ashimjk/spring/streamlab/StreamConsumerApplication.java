package io.ashimjk.spring.streamlab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(BeneficiaryStream.class)
public class StreamConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamConsumerApplication.class, args);
    }

}
