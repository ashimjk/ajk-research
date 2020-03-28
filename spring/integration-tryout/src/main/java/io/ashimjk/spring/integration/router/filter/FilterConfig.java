package io.ashimjk.spring.integration.router.filter;

import io.ashimjk.spring.integration.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.filter.MessageFilter;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class FilterConfig {

    @Bean
    @Filter(inputChannel = "filter.router.channel")
    public MessageFilter filterRouter() {
        MessageFilter filter = new MessageFilter(message -> message.getPayload() instanceof Student);
        filter.setOutputChannelName("filter.router.student.channel");
        return filter;
    }

}
