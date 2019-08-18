package io.ashimjk.eventstorepoc.event;

import io.ashimjk.eventstorepoc.domain.DeliveryAddress;
import io.ashimjk.eventstorepoc.domain.Name;
import io.ashimjk.eventstorepoc.domain.OrderPlaced;
import org.junit.jupiter.api.Test;

class EventStorePublisherTest {

    @Test
    void publishEvent() {
        new EventStorePublisher()
                .publishEvent(
                        new OrderPlaced("121", Name.create("a", "b", "c"),
                                new DeliveryAddress("ktm")));
    }
}
