package io.ashimjk.eventstorepoc.event;

import io.ashimjk.eventstorepoc.domain.DomainEvent;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
@Slf4j
public class EventStorePublisher {

    private final RestTemplate rest = new RestTemplate();

    @SneakyThrows
    public void publishEvent(DomainEvent o) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.put("ES-EventId", Collections.singletonList(o.getEventId()));
        headers.put("ES-EventType", Collections.singletonList(o.getEventType()));
        ResponseEntity<Object> response =
                rest.exchange(
                        "http://127.0.0.1:2113/streams" + "/" + "place-orders-stream",
                        HttpMethod.POST,
                        new HttpEntity<>(o, headers),
                        Object.class);

        Assert.isTrue(response.getStatusCode().is2xxSuccessful(), "Unable to store event [" + o + "]");
    }
}
