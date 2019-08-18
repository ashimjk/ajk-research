package io.ashimjk.eventstorepoc.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NameTest {

    @Test
    void firstNameIsRequired() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                Name.create("   ", "prasad", "upadhyay")
        );
        assertEquals(Name.FIRST_NAME_SHOULD_NOT_BE_EMPTY, exception.getMessage());
    }

    @Test
    void lastNameIsRequired() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                Name.create("bhuwan", "prasad", "")
        );
        assertEquals(Name.LAST_NAME_SHOULD_NOT_BE_EMPTY, exception.getMessage());
    }

    @Test
    void giveValidFirstAndLastName_whenCreate_thenShouldNameCreated() {
        Name name = Name.create("bhuwan", "prasad", "upadhyay");
        assertEquals("bhuwan", name.getFirstName());
        assertEquals("prasad", name.getMiddleName());
        assertEquals("upadhyay", name.getLastName());
    }
}
