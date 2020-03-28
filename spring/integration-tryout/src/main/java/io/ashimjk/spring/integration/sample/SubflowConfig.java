package io.ashimjk.spring.integration.sample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;

import java.util.concurrent.Executors;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class SubflowConfig {

    @Bean
    public IntegrationFlow subscribersFlow() {
        return flow -> flow
                .publishSubscribeChannel(
                        Executors.newCachedThreadPool(), s -> s
                                .subscribe(f -> f
                                        .handle(Integer.class, (p, h) -> p / 2)
                                        .channel(c -> c.queue("subscriber1Results")))
                                .subscribe(f -> f
                                        .<Integer>handle((p, h) -> p * 2)
                                        .channel(c -> c.queue("subscriber2Results")))
                )
                .<Integer>handle((p, h) -> p * 3)
                .channel(c -> c.queue("subscriber3Results"));
    }

    /**
     * The .channelMapping() continues to work as it does in regular Router mapping,
     * but the .subFlowMapping() tied that sub-flow to the main flow.
     *
     * @return IntegrationFlow
     */
    @Bean
    public IntegrationFlow routeFlow() {
        return f -> f
                .<Integer, Boolean>route(p -> p % 2 == 0,
                        m -> m
                                .channelMapping(Boolean.valueOf("true"), "evenChannel")
                                .subFlowMapping(Boolean.valueOf("false"), sf -> sf.handle(Integer.class, (p, h) -> p * 3)))
                .transform(Object::toString)
                .channel(c -> c.queue("oddChannel"));
    }

    /**
     * Refer to an existing IntegrationFlow @Bean from the .subFlowMapping()
     *
     * @return IntegrationFlow
     */
    @Bean
    public IntegrationFlow splitRouteAggregate() {
        return f -> f
                .split()
                .<Integer, Boolean>route(o -> o % 2 == 0,
                        m -> m
                                .subFlowMapping(true, oddFlow())
                                .subFlowMapping(false, sf -> sf.gateway(evenFlow())))
                .aggregate();
    }

    @Bean
    public IntegrationFlow oddFlow() {
        return f -> f.handle(m -> System.out.println("odd"));
    }

    @Bean
    public IntegrationFlow evenFlow() {
        return f -> f.handle((p, h) -> "even");
    }

}
