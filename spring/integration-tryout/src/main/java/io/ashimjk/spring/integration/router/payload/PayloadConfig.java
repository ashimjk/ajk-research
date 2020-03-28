package io.ashimjk.spring.integration.router.payload;

import io.ashimjk.spring.integration.Address;
import io.ashimjk.spring.integration.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.router.PayloadTypeRouter;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class PayloadConfig {

    @Bean
    @ServiceActivator(inputChannel = "payload.router.channel")
    public PayloadTypeRouter payloadRouter() {
        PayloadTypeRouter router = new PayloadTypeRouter();
        router.setChannelMapping(Student.class.getName(), "payload.router.student.channel");
        router.setChannelMapping(Address.class.getName(), "payload.router.address.channel");
        return router;
    }

}
