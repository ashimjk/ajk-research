package io.ashimjk.spring.integration.poc.directchannel2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

import java.util.List;

@SpringBootApplication
public class DirectChannel2App {

    @Autowired
    private TweetPublisher tweetPublisher;
    @Autowired
    private Tweeter tweeter;
    @Autowired
    private DirectChannel channel;

    public static void main(String[] args) {
        SpringApplication.run(DirectChannel2App.class, args);
    }

    @Bean
    public MessageChannel tweetChannel() {
        return new DirectChannel();
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context) {
        return args -> {
            channel.subscribe(new TweetReader());
            channel.subscribe(new TweetReader());
            channel.subscribe(new TweetReader());

            List<Tweet> tweets = tweetPublisher.getTweets();

            for (Tweet tweet : tweets) {
                tweeter.sendTweetReaders(tweet);
            }

            System.exit(SpringApplication.exit(context));
        };
    }

}
