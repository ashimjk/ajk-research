package io.ashimjk.spring.integration.router.header;

import io.ashimjk.spring.integration.Address;
import io.ashimjk.spring.integration.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.router.HeaderValueRouter;
import org.springframework.integration.router.PayloadTypeRouter;
import org.springframework.integration.transformer.HeaderEnricher;
import org.springframework.integration.transformer.support.HeaderValueMessageProcessor;
import org.springframework.integration.transformer.support.StaticHeaderValueMessageProcessor;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class HeaderConfig {

    @Bean
    @ServiceActivator(inputChannel = "header.router.channel")
    public PayloadTypeRouter headerPayloadRouter() {
        PayloadTypeRouter router = new PayloadTypeRouter();
        router.setChannelMapping(Student.class.getName(), "student.enrich.header.channel");
        router.setChannelMapping(Address.class.getName(), "address.enrich.header.channel");
        return router;
    }

    @Bean
    @Transformer(inputChannel = "student.enrich.header.channel",
            outputChannel = "header.payload.router.channel")
    public HeaderEnricher studentHeaderRouterEnricher() {
        Map<String, HeaderValueMessageProcessor<String>> headersToAdd = new HashMap<>();
        headersToAdd.put("testHeader", new StaticHeaderValueMessageProcessor<>("student"));
        return new HeaderEnricher(headersToAdd);
    }

    @Bean
    @Transformer(inputChannel = "address.enrich.header.channel",
            outputChannel = "header.payload.router.channel")
    public HeaderEnricher addressHeaderRouterEnricher() {
        Map<String, HeaderValueMessageProcessor<String>> headersToAdd = new HashMap<>();
        headersToAdd.put("testHeader", new StaticHeaderValueMessageProcessor<>("address"));
        return new HeaderEnricher(headersToAdd);
    }

    @Bean
    @ServiceActivator(inputChannel = "header.payload.router.channel")
    public HeaderValueRouter headerValueRouter() {
        HeaderValueRouter router = new HeaderValueRouter("testHeader");
        router.setChannelMapping("student", "header.router.student.channel");
        router.setChannelMapping("address", "header.router.address.channel");
        return router;
    }

}
