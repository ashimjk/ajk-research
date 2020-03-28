package io.ashimjk.spring.integration.gateway;

import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class IntegrationConfiguration {

    //@Bean
    //public MessageChannel receiverChannel() {
    //    return new DirectChannel();
    //}
    //
    //@Bean
    //public MessageChannel replyChannel() {
    //    return new DirectChannel();
    //}

}
