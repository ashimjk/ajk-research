package io.ashimjk.chat.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {

    private MessageType type;
    private String content;
    private String sender;

    @Getter
    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

}
