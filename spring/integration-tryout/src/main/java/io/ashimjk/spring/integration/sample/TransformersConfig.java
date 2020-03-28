package io.ashimjk.spring.integration.sample;

import io.ashimjk.spring.integration.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Transformers;

public class TransformersConfig {

    @Bean
    public IntegrationFlow transformFlow() {
        return IntegrationFlows.from("input")
                .transform(Transformers.fromJson(Student.class))
                .transform(Transformers.serializer())
                .get();
    }

}
