package io.ashimjk.spring.integration.sample;

import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class ProtocolAdapterConfig {

    //@Bean
    //public IntegrationFlow amqpFlow() {
    //    return IntegrationFlows.from(Amqp.inboundGateway(this.rabbitConnectionFactory, queue()))
    //            .transform("hello "::concat)
    //            .transform(String.class, String::toUpperCase)
    //            .get();
    //}
    //
    //@Bean
    //public IntegrationFlow jmsOutboundGatewayFlow() {
    //    return IntegrationFlows.from("jmsOutboundGatewayChannel")
    //            .handle(Jms.outboundGateway(this.jmsConnectionFactory)
    //                    .replyContainer(c ->
    //                            c.concurrentConsumers(3)
    //                                    .sessionTransacted(true))
    //                    .requestDestination("jmsPipelineTest"))
    //            .get();
    //}
    //
    //@Bean
    //public IntegrationFlow sendMailFlow() {
    //    return IntegrationFlows.from("sendMailChannel")
    //            .handle(Mail.outboundAdapter("localhost")
    //                            .port(smtpPort)
    //                            .credentials("user", "pw")
    //                            .protocol("smtp")
    //                            .javaMailProperties(p -> p.put("mail.debug", "true")),
    //                    e -> e.id("sendMailEndpoint"))
    //            .get();
    //}

    //@Bean
    //public QueueChannelSpec wrongMessagesChannel() {
    //    return MessageChannels
    //            .queue()
    //            .wireTap("wrongMessagesWireTapChannel");
    //}
    //
    //@Bean
    //public IntegrationFlow xpathFlow(MessageChannel wrongMessagesChannel) {
    //    return IntegrationFlows.from("inputChannel")
    //            .filter(new StringValueTestXPathMessageSelector("namespace-uri(/*)", "my:namespace"),
    //                    e -> e.discardChannel(wrongMessagesChannel))
    //            .log(LoggingHandler.Level.ERROR, "test.category", m -> m.getHeaders().getId())
    //            .route(xpathRouter(wrongMessagesChannel))
    //            .get();
    //}
    //
    //@Bean
    //public AbstractMappingMessageRouter xpathRouter(MessageChannel wrongMessagesChannel) {
    //    XPathRouter router = new XPathRouter("local-name(/*)");
    //    router.setEvaluateAsString(true);
    //    router.setResolutionRequired(false);
    //    router.setDefaultOutputChannel(wrongMessagesChannel);
    //    router.setChannelMapping("Tags", "splittingChannel");
    //    router.setChannelMapping("Tag", "receivedChannel");
    //    return router;
    //}

}
