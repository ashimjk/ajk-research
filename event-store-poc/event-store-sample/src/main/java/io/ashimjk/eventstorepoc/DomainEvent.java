package io.ashimjk.eventstorepoc;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DomainEvent {

    private String id;
    private String name;

}
