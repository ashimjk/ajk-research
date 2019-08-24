package io.ashimjk.eventstorepoc;

import lombok.Data;

@Data
public class OrderPlaceCommand {
    private String firstName;
    private String lastName;
    private String middleName;
    private String location;
}
