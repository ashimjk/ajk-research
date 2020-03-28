package io.ashimjk.spring.integration.config;

import io.ashimjk.spring.integration.domain.Beneficiary;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.mongodb.outbound.MongoDbOutboundGateway;
import org.springframework.messaging.MessageHandler;

@Configuration
@EnableIntegration
@IntegrationComponentScan
@RequiredArgsConstructor
public class MongoDbConfig {

    private final MongoDbFactory mongoDbFactory;

    @Bean
    @ServiceActivator(inputChannel = "requestChannel")
    public MessageHandler mongoDbOutboundGateway() {
        MongoDbOutboundGateway gateway = new MongoDbOutboundGateway(this.mongoDbFactory);
        gateway.setCollectionNameExpressionString("'beneficiary'");
        gateway.setQueryExpressionString("'{}'");
        gateway.setEntityClass(Beneficiary.class);
        gateway.setOutputChannelName("replyChannel");
        return gateway;
    }

    //@Bean
    //@ServiceActivator(inputChannel = "replyChannel")
    //public MessageHandler handler() {
    //    return message -> System.out.println(message.getPayload());
    //}

}
