package io.ashimjk.spring.messaging.headerExchange;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeaderExchangeConfig {

    public static final String HEADER_EXCHANGE = "header_exchange";

    public static final String ROUTING_KEY_ADMIN = "header_admin_routing_key";
    public static final String ROUTING_KEY_MARKETING = "header_marketing_routing_key";
    public static final String ROUTING_KEY_FINANCE = "header_finance_routing_key";

    private static final String HEADER_ADMIN_QUEUE = "header_admin_queue";
    private static final String HEADER_MARKETING_QUEUE = "header_marketing_queue";
    private static final String HEADER_FINANCE_QUEUE = "header_finance_queue";

    @Bean
    public Queue headerAdminQueue() {
        return new Queue(HEADER_ADMIN_QUEUE);
    }

    @Bean
    public Queue headerMarketingQueue() {
        return new Queue(HEADER_MARKETING_QUEUE);
    }

    @Bean
    public Queue headerFinanceQueue() {
        return new Queue(HEADER_FINANCE_QUEUE);
    }

    @Bean
    public HeadersExchange headerExchange() {
        return new HeadersExchange(HEADER_EXCHANGE);
    }

    @Bean
    public Binding adminQueueToHeaderExchangeBinding() {
        return BindingBuilder.bind(headerAdminQueue()).to(headerExchange()).where("department").matches(ROUTING_KEY_ADMIN);
    }

    @Bean
    public Binding marketingQueueToHeaderExchangeBinding() {
        return BindingBuilder.bind(headerMarketingQueue()).to(headerExchange()).where("department").matches(ROUTING_KEY_MARKETING);
    }

    @Bean
    public Binding financeQueueToHeaderExchangeBinding() {
        return BindingBuilder.bind(headerFinanceQueue()).to(headerExchange()).where("department").matches(ROUTING_KEY_FINANCE);
    }

}
