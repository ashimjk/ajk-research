package io.ashimjk.eventhandling.sync;

import org.springframework.context.ApplicationEvent;

class CustomEvent extends ApplicationEvent {

    private String message;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    CustomEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    String getMessage() {
        return message;
    }

}
