package io.ashimjk.spring.messaging.topicExchange;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicExchangeConfig {

    public static final String TOPIC_EXCHANGE = "topic_exchange";

    public static final String ROUTING_KEY_ADMIN = "topic_admin_routing_key";
    public static final String ROUTING_KEY_MARKETING = "topic_marketing_routing_key";
    public static final String ROUTING_KEY_FINANCE = "topic_finance_routing_key";
    public static final String ROUTING_KEY_ALL = "*";

    private static final String TOPIC_ADMIN_QUEUE = "topic_admin_queue";
    private static final String TOPIC_MARKETING_QUEUE = "topic_marketing_queue";
    private static final String TOPIC_FINANCE_QUEUE = "topic_finance_queue";
    private static final String TOPIC_ALL_QUEUE = "topic_all_queue";

    @Bean
    public Queue topicAdminQueue() {
        return new Queue(TOPIC_ADMIN_QUEUE);
    }

    @Bean
    public Queue topicMarketingQueue() {
        return new Queue(TOPIC_MARKETING_QUEUE);
    }

    @Bean
    public Queue topicFinanceQueue() {
        return new Queue(TOPIC_FINANCE_QUEUE);
    }

    @Bean
    public Queue topicAllQueue() {
        return new Queue(TOPIC_ALL_QUEUE);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding adminQueueToTopicExchangeBinding() {
        return BindingBuilder.bind(topicAdminQueue()).to(topicExchange()).with(ROUTING_KEY_ADMIN);
    }

    @Bean
    public Binding marketingQueueToTopicExchangeBinding() {
        return BindingBuilder.bind(topicMarketingQueue()).to(topicExchange()).with(ROUTING_KEY_MARKETING);
    }

    @Bean
    public Binding financeQueueToTopicExchangeBinding() {
        return BindingBuilder.bind(topicFinanceQueue()).to(topicExchange()).with(ROUTING_KEY_FINANCE);
    }

    @Bean
    public Binding allQueueToTopicExchangeBinding() {
        return BindingBuilder.bind(topicAllQueue()).to(topicExchange()).with(ROUTING_KEY_ALL);
    }

}
