package io.ashimjk.eventstorepoc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
        classes = EventStorePocApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class EventStorePocApplicationTest {

    @Autowired
    private TestRestTemplate rest;

    @Test
    void publishEvent() {
        DomainEvent domainEvent = new DomainEvent("123", "ashim123");

        ResponseEntity<DomainEvent> response = rest.postForEntity("/events", domainEvent, DomainEvent.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
