package io.ashimjk.spring.integration.poc.message.processing.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;

/**
 * Configuration provider class for Spring Container.
 */
@Configuration
@ComponentScan("io.ashimjk.spring.integration.poc.message.processing.integration")
@EnableIntegration
@IntegrationComponentScan("io.ashimjk.spring.integration.poc.message.processing.integration")
public class AppConfiguration {

    /**
     * Creates a new cargoGWDefaultRequest Channel and registers to BeanFactory.
     *
     * @return direct channel
     */
    @Bean
    public MessageChannel cargoGWDefaultRequestChannel() {
        return new DirectChannel();
    }

    /**
     * Creates a new cargoSplitterOutput Channel and registers to BeanFactory.
     *
     * @return direct channel
     */
    @Bean
    public MessageChannel cargoSplitterOutputChannel() {
        return new DirectChannel();
    }

    /**
     * Creates a new cargoFilterOutput Channel and registers to BeanFactory.
     *
     * @return direct channel
     */
    @Bean
    public MessageChannel cargoFilterOutputChannel() {
        return new DirectChannel();
    }

    /**
     * Creates a new cargoFilterDiscard Channel and registers to BeanFactory.
     *
     * @return direct channel
     */
    @Bean
    public MessageChannel cargoFilterDiscardChannel() {
        return new DirectChannel();
    }

    /**
     * Creates a new cargoRouterDomesticOutput Channel and registers to BeanFactory.
     *
     * @return direct channel
     */
    @Bean
    public MessageChannel cargoRouterDomesticOutputChannel() {
        return new DirectChannel();
    }

    /**
     * Creates a new cargoRouterInternationalOutput Channel and registers to BeanFactory.
     *
     * @return direct channel
     */
    @Bean
    public MessageChannel cargoRouterInternationalOutputChannel() {
        return new DirectChannel();
    }

    /**
     * Creates a new cargoTransformerOutput Channel and registers to BeanFactory.
     *
     * @return direct channel
     */
    @Bean
    public MessageChannel cargoTransformerOutputChannel() {
        return new DirectChannel();
    }

}
