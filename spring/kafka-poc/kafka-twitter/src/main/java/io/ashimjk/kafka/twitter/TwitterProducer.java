package io.ashimjk.kafka.twitter;

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TwitterProducer {

    private final String consumerKey = "FuycpnRAWiGCDlLVmRkD1iYui";
    private final String consumerSecret = "orFxADPAtw8QLiTUY8h8ZEOBgyspx9BrpLT0p5Q06O4VoDa1B4";
    private final String token = "1166413675776757760-ibjmzRALPgGLqSSBwsHHgQNuQUsvRO";
    private final String secret = "998TbHJTRpNLemTheZInDomDoR7n3X7eUS5542H3Z3ipj";

    public static void main(String[] args) {
        new TwitterProducer().run();
    }

    private void run() {
        BlockingQueue<String> msgQueue = new LinkedBlockingQueue<>(100);

        // create a twitter client
        final Client twitterClient = createTwitterClient(msgQueue);

        // attempts to establish a connection
        twitterClient.connect();

        // create a kafka producer
        // code to send data to kafka

        // loop to send tweets to kafka
        while (!twitterClient.isDone()) {
            String msg = null;

            try {
                msg = msgQueue.poll(1, TimeUnit.SECONDS);
//                msg = msgQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
                twitterClient.stop();
            }

            if (msg != null) {
                System.out.println(msg);
            }
        }
    }

    private Client createTwitterClient(BlockingQueue<String> msgQueue) {

        /* Declare the host you want to connect to, the endpoint, and authentication (basic auth or oauth) */
        Hosts hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
        StatusesFilterEndpoint hosebirdEndpoint = new StatusesFilterEndpoint();

        List<String> terms = Lists.newArrayList("kafka");
        hosebirdEndpoint.trackTerms(terms);

        // These secrets should be read from a config file
        Authentication hosebirdAuth = new OAuth1(consumerKey, consumerSecret, token, secret);

        ClientBuilder builder = new ClientBuilder()
                .name("Hosebird-Client-01")                              // optional: mainly for the logs
                .hosts(hosebirdHosts)
                .authentication(hosebirdAuth)
                .endpoint(hosebirdEndpoint)
                .processor(new StringDelimitedProcessor(msgQueue));                          // optional: use this if you want to process client events

        return builder.build();
    }

}
