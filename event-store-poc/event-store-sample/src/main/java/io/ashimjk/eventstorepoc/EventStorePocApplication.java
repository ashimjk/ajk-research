package io.ashimjk.eventstorepoc;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class EventStorePocApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventStorePocApplication.class, args);
    }

    @RestController
    @RequestMapping("/events")
    @RequiredArgsConstructor
    static class EventApi {

        private final PublishEvent publishEvent;

        @PostMapping
        public ResponseEntity<DomainEvent> publish(@RequestBody DomainEvent domainEvent) {
            return ResponseEntity.ok(publishEvent.publish(domainEvent));
        }
    }

}
