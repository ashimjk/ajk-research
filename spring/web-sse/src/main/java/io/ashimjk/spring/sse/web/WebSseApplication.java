package io.ashimjk.spring.sse.web;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class WebSseApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSseApplication.class, args);
    }

    @RestController
    public static class ResponseBodyEmitterController {

        private ExecutorService executor = Executors.newCachedThreadPool();

        @GetMapping("/rbe")
        public ResponseEntity<ResponseBodyEmitter> handleRbe() {
            ResponseBodyEmitter emitter = new ResponseBodyEmitter();

            executor.submit(() -> {
                try {
                    emitter.send("/rbe" + " @ " + new Date(), MediaType.TEXT_PLAIN);
                } catch (Exception ignored) {
                }
            });

            return new ResponseEntity<>(emitter, HttpStatus.OK);
        }

    }

}
