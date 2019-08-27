package io.ashimjk.spring.integration.poc.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApp {

    @Autowired
    private IntegrationGateway integrationGateway;

    public static void main(String[] args) {
        SpringApplication.run(GatewayApp.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ConfigurableApplicationContext context) {
        return args -> {
            String message = "ashim";
            String receiveMessage = integrationGateway.sendMessage(message);

            System.out.println(receiveMessage);

            context.close();
        };
    }

}
