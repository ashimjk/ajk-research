package io.ashimjk.cucumber;

import org.springframework.stereotype.Component;

@Component
public class MessageProvider {

    public String getMessage() {
        return "Hello World!";
    }

}
