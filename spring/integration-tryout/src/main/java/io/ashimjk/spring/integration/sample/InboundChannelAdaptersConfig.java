package io.ashimjk.spring.integration.sample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.Transformers;
import org.springframework.integration.jdbc.JdbcPollingChannelAdapter;

import javax.sql.DataSource;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class InboundChannelAdaptersConfig {

    private DataSource dataSource;

    @Bean
    public MessageSource<Object> jdbcMessageSource() {
        return new JdbcPollingChannelAdapter(this.dataSource, "SELECT * FROM something");
    }

    @Bean
    public IntegrationFlow pollingFlow() {
        return IntegrationFlows.from(jdbcMessageSource(),
                c -> c.poller(Pollers.fixedRate(100).maxMessagesPerPoll(1)))
                .transform(Transformers.toJson())
                .channel("furtherProcessChannel")
                .get();
    }

}
