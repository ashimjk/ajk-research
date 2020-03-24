package io.ashimjk.spring.sse.regular;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.file.dsl.Files;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@SpringBootApplication
public class RegularSseApplication {

    private final Map<String, SseEmitter> sses = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(RegularSseApplication.class, args);
    }

    @Bean
    public IntegrationFlow integrationFlow(@Value("${input-dir:file://${HOME}/Desktop/in}") File in) {
        return IntegrationFlows.from(Files.inboundAdapter(in).autoCreateDirectory(true),
                polling -> polling.poller(spec -> spec.fixedRate(1000L)))
                .transform(File.class, File::getAbsolutePath)
                .handle(String.class, (path, headers) -> {
                    sses.forEach((userId, emitter) -> {
                        try {
                            emitter.send(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    return null;
                })
                .get();
    }

    @GetMapping("/sse")
    public SseEmitter handleSse() {
        String userId = UUID.randomUUID().toString();
        SseEmitter sseEmitter = new SseEmitter(60 * 1000L);
        sses.put(userId, sseEmitter);
        return sseEmitter;
    }

}
