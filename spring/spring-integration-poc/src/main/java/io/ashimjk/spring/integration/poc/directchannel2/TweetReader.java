package io.ashimjk.spring.integration.poc.directchannel2;

import org.springframework.integration.MessageRejectedException;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
public class TweetReader implements MessageHandler {

    @Override
    public void handleMessage(Message<?> message)
            throws MessagingException {
        Object payload = message.getPayload();
        if (payload instanceof Tweet) {
            receiveAndAcknowledge((Tweet) payload);
        } else {
            throw new MessageRejectedException(message,
                    "Unknown data type has been received.");
        }
    }

    private void receiveAndAcknowledge(Tweet tweet) {
        System.out.println("Hi Tweeter, this is Reader #"
                + System.identityHashCode(this) +
                "." + "Received tweet - " + tweet.toString()
                + "\n");
    }

}