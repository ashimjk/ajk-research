package io.ashimjk.spring.integration.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.json.ObjectToJsonTransformer;

import java.io.File;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class FileOutputHandlerConfig {

    @Bean
    public IntegrationFlow fileOutputHandler(@Value("${output-dir:classpath:output}") File out) {
        return IntegrationFlows.from("fileOutputChannel")
                .log()
                .transform(new ObjectToJsonTransformer())
                .handle(Files.outboundAdapter(out).autoCreateDirectory(true))
                .get();
    }

}
