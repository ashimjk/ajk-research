package io.ashimjk.spring.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RabbitmqMessagingApplication {

    public static final String EXCHANGE_NAME = "ajk_exchange";
    public static final String QUEUE = "ajk_queue";
    public static final String ROUTING_KEY = "ajk_routing_key";

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqMessagingApplication.class, args);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue defaultParsingQueue() {
        return new Queue(QUEUE);
    }

    @Bean
    public Binding queueToExchangeBinding() {
        return BindingBuilder.bind(defaultParsingQueue()).to(topicExchange()).with(ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
        listenerContainer.setConnectionFactory(connectionFactory);
        listenerContainer.setQueueNames(QUEUE);
        listenerContainer.setMessageListener(System.out::println);
        return listenerContainer;
    }

}
