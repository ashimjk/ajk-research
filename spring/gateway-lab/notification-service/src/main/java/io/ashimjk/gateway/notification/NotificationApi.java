package io.ashimjk.gateway.notification;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("notification")
public class NotificationApi {

    @GetMapping("service")
    public ResponseEntity<Service> getService() {
        Service service = new Service("notification-service");
        return ResponseEntity.ok(service);
    }

    @GetMapping("service1")
    public Mono<Service> getService1() {
        Service service = new Service("notification-service");
        return Mono.just(service);
    }

}
