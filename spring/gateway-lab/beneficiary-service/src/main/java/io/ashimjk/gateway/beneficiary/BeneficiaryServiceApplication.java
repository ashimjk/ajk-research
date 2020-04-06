package io.ashimjk.gateway.beneficiary;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication
public class BeneficiaryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeneficiaryServiceApplication.class, args);
    }

    @RestController
    @RequestMapping()
    public static class BeneficiaryApi {

        @GetMapping("service")
        public Mono<Service> getService() {
            Service service = new Service("beneficiary-service");
            return Mono.just(service);
        }

        @GetMapping(value = "subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
        public Flux<String> subscribe() {
            return Flux.interval(Duration.ofSeconds(1))
                    .map(i -> i + ". " + LocalDateTime.now());
        }

    }

    @Data
    @AllArgsConstructor
    public static class Service {
        private String name;

    }

}
