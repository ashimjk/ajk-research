package io.ashimjk.eventstorepoc.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class DomainEvent {

    private String eventId;
    private String eventType;
}
