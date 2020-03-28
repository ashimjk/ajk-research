package io.ashimjk.spring.integration.transformer;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ashimjk.spring.integration.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.json.JsonToObjectTransformer;
import org.springframework.integration.json.ObjectToJsonTransformer;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.integration.transformer.HeaderEnricher;
import org.springframework.integration.transformer.support.HeaderValueMessageProcessor;
import org.springframework.integration.transformer.support.StaticHeaderValueMessageProcessor;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class TransformerConfig {

    @Bean
    @Transformer(inputChannel = "integration.transformer.gateway.channel",
            outputChannel = "integration.transformer.toConvertObject.channel")
    public HeaderEnricher transformerHeaderEnricher() {
        Map<String, HeaderValueMessageProcessor<String>> headersToAdd = new HashMap<>();
        headersToAdd.put("header1", new StaticHeaderValueMessageProcessor<>("Test Header 1"));
        headersToAdd.put("header2", new StaticHeaderValueMessageProcessor<>("Test Header 2"));
        return new HeaderEnricher(headersToAdd);
    }

    @Bean
    @Transformer(inputChannel = "integration.transformer.toConvertObject.channel",
            outputChannel = "integration.transformer.objectToJson.channel")
    public ObjectToJsonTransformer objectToJsonTransformer() {
        return new ObjectToJsonTransformer(getMapper());
    }

    private Jackson2JsonObjectMapper getMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonObjectMapper(objectMapper);
    }

    @Bean
    @Transformer(inputChannel = "integration.transformer.jsonToObject.channel",
            outputChannel = "integration.transformer.jsonToObject.fromTransformer.channel")
    public JsonToObjectTransformer jsonToObjectTransformer() {
        return new JsonToObjectTransformer(Student.class);
    }

}
