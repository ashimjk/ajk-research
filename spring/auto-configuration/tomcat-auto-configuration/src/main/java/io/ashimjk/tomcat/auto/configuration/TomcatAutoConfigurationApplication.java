package io.ashimjk.tomcat.auto.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class TomcatAutoConfigurationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TomcatAutoConfigurationApplication.class, args);
    }

}
