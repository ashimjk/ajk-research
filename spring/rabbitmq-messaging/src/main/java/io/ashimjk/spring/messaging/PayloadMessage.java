package io.ashimjk.spring.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayloadMessage {

    private String text;
    private int priority;
    private boolean secret;

}
