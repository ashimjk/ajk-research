package io.ashimjk.lazyinit;

import io.ashimjk.lazyinit.dependency.EntitlementApprovalHandler;
import io.ashimjk.lazyinit.dependency.RestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "io.ashimjk.lazyinit")
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public EntitlementApprovalHandler entitlementApprovalHandler() {
        return new EntitlementApprovalHandler();
    }

}