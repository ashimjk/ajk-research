package io.ashimjk.spring.messaging.directExchange;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectExchangeConfig {

    public static final String DIRECT_EXCHANGE = "direct_exchange";

    public static final String ROUTING_KEY_ADMIN = "direct_admin_routing_key";
    public static final String ROUTING_KEY_MARKETING = "direct_marketing_routing_key";
    public static final String ROUTING_KEY_FINANCE = "direct_finance_routing_key";

    private static final String DIRECT_ADMIN_QUEUE = "direct_admin_queue";
    private static final String DIRECT_MARKETING_QUEUE = "direct_marketing_queue";
    private static final String DIRECT_FINANCE_QUEUE = "direct_finance_queue";

    @Bean
    public Queue directAdminQueue() {
        return new Queue(DIRECT_ADMIN_QUEUE);
    }

    @Bean
    public Queue directMarketingQueue() {
        return new Queue(DIRECT_MARKETING_QUEUE);
    }

    @Bean
    public Queue directFinanceQueue() {
        return new Queue(DIRECT_FINANCE_QUEUE);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public Binding adminQueueToDirectExchangeBinding() {
        return BindingBuilder.bind(directAdminQueue()).to(directExchange()).with(ROUTING_KEY_ADMIN);
    }

    @Bean
    public Binding marketingQueueToDirectExchangeBinding() {
        return BindingBuilder.bind(directMarketingQueue()).to(directExchange()).with(ROUTING_KEY_MARKETING);
    }

    @Bean
    public Binding financeQueueToDirectExchangeBinding() {
        return BindingBuilder.bind(directFinanceQueue()).to(directExchange()).with(ROUTING_KEY_FINANCE);
    }

}
