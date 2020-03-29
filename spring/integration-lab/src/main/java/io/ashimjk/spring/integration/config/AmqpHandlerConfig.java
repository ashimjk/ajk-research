package io.ashimjk.spring.integration.config;

import io.ashimjk.spring.integration.domain.Beneficiary;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class AmqpHandlerConfig {

    @Bean
    public IntegrationFlow amqpOutputIntegrationFlow(AmqpTemplate amqpTemplate) {
        return IntegrationFlows.from("amqpOutputChannel")
                .handle(Amqp.outboundAdapter(amqpTemplate)
                        .exchangeName("fileExchange")
                        .routingKey("fileQueue")
                )
                .get();
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

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate amqpTemplate = new RabbitTemplate(connectionFactory);
        amqpTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return amqpTemplate;
    }

    private Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
        jackson2JsonMessageConverter.setClassMapper(getClassMapper());
        return jackson2JsonMessageConverter;
    }

    private ClassMapper getClassMapper() {
        return new ClassMapper() {
            @Override
            public void fromClass(Class<?> clazz, MessageProperties properties) {
            }

            @Override
            public Class<?> toClass(MessageProperties properties) {
                return Beneficiary.class;
            }
        };
    }

}
