package io.ashimjk.spring.integration.poc.directchannel2;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
class TweetPublisher {

    private static long id;

    List<Tweet> getTweets() {
        List<Tweet> tweets = new ArrayList<>();
        tweets.add(createTweet("Storms in Pacific", "#weather"));
        tweets.add(createTweet("what's up developers?", "#dev"));
        tweets.add(createTweet("Chinese delicacy in Amazon",
                "#traveller"));
        tweets.add(createTweet("inflation down by 2%", "#stock"));
        tweets.add(createTweet("save river", "#environment"));
        tweets.add(createTweet("New star found", "#astronaut"));
        tweets.add(createTweet("Learn math quickly", "#tutor"));
        tweets.add(createTweet("Save animals", "#bovine"));
        tweets.add(createTweet("stars are favorable now",
                "#astro"));
        tweets.add(createTweet("social unrest in the world",
                "#concern"));
        return tweets;
    }

    private Tweet createTweet(String text, String hashTag) {
        Tweet tweet = new Tweet();
        tweet.setTid(id++);
        tweet.setHashTag(hashTag);
        tweet.setText(text);
        tweet.setTime(new Date());
        return tweet;
    }

}