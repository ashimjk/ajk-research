package io.ashimjk.spring.messaging.fanoutExchange;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutExchangeConfig {

    public static final String FANOUT_EXCHANGE = "fanout_exchange";

    public static final String ROUTING_KEY_ADMIN = "fanout_admin_routing_key";
    public static final String ROUTING_KEY_MARKETING = "fanout_marketing_routing_key";
    public static final String ROUTING_KEY_FINANCE = "fanout_finance_routing_key";

    private static final String FANOUT_ADMIN_QUEUE = "fanout_admin_queue";
    private static final String FANOUT_MARKETING_QUEUE = "fanout_marketing_queue";
    private static final String FANOUT_FINANCE_QUEUE = "fanout_finance_queue";

    @Bean
    public Queue fanoutAdminQueue() {
        return new Queue(FANOUT_ADMIN_QUEUE);
    }

    @Bean
    public Queue fanoutMarketingQueue() {
        return new Queue(FANOUT_MARKETING_QUEUE);
    }

    @Bean
    public Queue fanoutFinanceQueue() {
        return new Queue(FANOUT_FINANCE_QUEUE);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Binding adminQueueToFanoutExchangeBinding() {
        return BindingBuilder.bind(fanoutAdminQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding marketingQueueToFanoutExchangeBinding() {
        return BindingBuilder.bind(fanoutMarketingQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding financeQueueToFanoutExchangeBinding() {
        return BindingBuilder.bind(fanoutFinanceQueue()).to(fanoutExchange());
    }

}
