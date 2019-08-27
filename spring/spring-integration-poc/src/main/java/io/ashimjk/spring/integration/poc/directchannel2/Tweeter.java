package io.ashimjk.spring.integration.poc.directchannel2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class Tweeter {

    private DirectChannel channel;

    @Value("#{tweetChannel}")
    public void setChannel(DirectChannel channel) {
        this.channel = channel;
    }

    void sendTweetReaders(Tweet tweet) {
        System.out.println("New Tweet = " + tweet.toString());
        channel.send(MessageBuilder.withPayload(tweet).build());
    }

}
