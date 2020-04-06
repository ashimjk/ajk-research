package io.ashimjk.gateway.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @RestController
    @RequestMapping("notification")
    public static class NotificationApi {

        @GetMapping("service")
        public Mono<Service> getService() {
            Service service = new Service("notification-service");
            return Mono.just(service);
        }

    }

    @Data
    @AllArgsConstructor
    public static class Service {
        private String name;

    }

}
