package io.ashimjk.spring.integration.router.recipient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.router.RecipientListRouter;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class RecipientConfig {

    @Bean
    @ServiceActivator(inputChannel = "recipient.router.channel")
    public RecipientListRouter recipient() {
        RecipientListRouter router = new RecipientListRouter();
        router.addRecipient("recipient.router.student.channel.1");
        router.addRecipient("recipient.router.student.channel.2");
        return router;
    }

}
