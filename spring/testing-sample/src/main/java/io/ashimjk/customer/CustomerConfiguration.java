package io.ashimjk.customer;

import io.ashimjk.customer.business.CustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class CustomerConfiguration {

    @Bean
    public CustomerService customerService() {
        return new CustomerService();
    }

}
