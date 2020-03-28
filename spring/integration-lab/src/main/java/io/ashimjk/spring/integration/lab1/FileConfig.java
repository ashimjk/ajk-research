package io.ashimjk.spring.integration.lab1;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.dsl.Files;

import java.io.File;

public class FileConfig {

    @Bean
    public IntegrationFlow flowIntegrationFlow(@Value("${input-dir:file://${HOME}/Desktop/in}") File in,
                                               @Value("${input-dir:file://${HOME}/Desktop/out}") File out,
                                               AmqpTemplate amqpTemplate) {
        return IntegrationFlows.from(
                Files.inboundAdapter(in).autoCreateDirectory(true),
                polling -> polling.poller(Pollers.fixedRate(3000))
        )
                .transform(Files.toStringTransformer())
                .log()
                //.handle(message -> {
                //    System.out.println("File Adapter");
                //    System.out.println(message.getPayload());
                //})
                .transform(String.class, String::toUpperCase)
                //.handle(Files.outboundAdapter(out))
                .handle(Amqp.outboundAdapter(amqpTemplate).exchangeName("fileExchange").routingKey("fileQueue"))
                .get();
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate amqpTemplate = new RabbitTemplate(connectionFactory);
        amqpTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return amqpTemplate;
    }

    @Bean
    public IntegrationFlow amqpIntegrationFlow(ConnectionFactory connectionFactory) {
        return IntegrationFlows.from(Amqp.inboundAdapter(connectionFactory, "fileQueue"))
                .handle(String.class, (s, headers) -> {
                    System.out.println("### Inside Amqp Inbound ###");
                    System.out.println(s);
                    return null;
                })
                .get();
    }

}
