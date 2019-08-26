package io.ashimjk.spring.integration.poc.directchannel.pub;

import io.ashimjk.spring.integration.poc.directchannel.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * publisher endpoint
 */
@Component
@RequiredArgsConstructor
public class Librarian {

    private final DirectChannel channel;

    public void sendPremiumReaders(Book book) {
        System.out.println("Dear Premium Reader, Just Arrived - " + book.toString());
        channel.send(MessageBuilder.withPayload(book).build());
    }
}
