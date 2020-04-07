package io.ashimjk.gateway.notification;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class NotificationApi {

    @GetMapping("service")
    public ResponseEntity<Service> getService() {
        Service service = new Service("notification-web-service");
        return ResponseEntity.ok(service);
    }

    @GetMapping("service1")
    public Mono<Service> getService1() {
        Service service = new Service("notification-mono-service");
        return Mono.just(service);
    }

}
