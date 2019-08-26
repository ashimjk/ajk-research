package io.ashimjk.spring.integration.poc.directchannel.sub;

import io.ashimjk.spring.integration.poc.directchannel.model.Book;
import org.springframework.integration.MessageRejectedException;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

/**
 * subscriber endpoint
 */
@Component
public class PremiumReader implements MessageHandler {

    @Override
    public void handleMessage(Message<?> message) {
        Object payload = message.getPayload();

        if (payload instanceof Book) {
            receiveAndAcknowledge((Book) payload);
        } else {
            throw new MessageRejectedException(message, "Unknown data type has been received.");
        }
    }

    private void receiveAndAcknowledge(Book book) {
        System.out.println("Hi Librarian, this is Reader #" + System.identityHashCode(this) +
                ". " + "Received book - " + book.toString() + "\n");
    }
}
