package io.ashimjk.spring.integration.sample;

import org.aopalliance.aop.Advice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.transformer.PayloadSerializingTransformer;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class EndpointConfig {

    @Bean
    MessageChannel inputChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public IntegrationFlow flow2() {
        return IntegrationFlows.from(inputChannel())
                .transform(new PayloadSerializingTransformer(),
                        c -> c.autoStartup(false).id("payloadSerializingTransformer"))
                .transform((Integer p) -> p * 2, c -> c.advice(this.expressionAdvice()))
                .get();
    }

    private Advice expressionAdvice() {
        return null;
    }

    //@Bean
    //public TcpOutboundGateway tcpOut() {
    //    TcpOutboundGateway gateway = new TcpOutboundGateway();
    //    gateway.setConnectionFactory(cf());
    //    gateway.setAdviceChain(Collections.singletonList(fooAdvice()));
    //    return gateway;
    //}
    //
    //@Bean
    //public IntegrationFlow clientTcpFlow() {
    //    return f -> f
    //            .handle(tcpOut(), e -> e.advice(testAdvice()))
    //            .transform(Transformers.objectToString());
    //}
}
