package io.ashimjk.eventstorepoc;

import akka.actor.ActorSystem;
import eventstore.akka.SubscriptionObserver;
import eventstore.core.Event;
import eventstore.core.UserCredentials;
import eventstore.j.EsConnection;
import eventstore.j.EsConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.Closeable;

@SpringBootApplication
@Slf4j
public class DeliverServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(DeliverServiceApp.class, args);
    }

    @EventListener
    public void onceServiceReady(ApplicationReadyEvent event) {

        final ActorSystem system = ActorSystem.create();
        final EsConnection connection = EsConnectionFactory.create(system);
        connection.subscribeToStream(
                "place-orders-stream",
                new SubscriptionObserver<Event>() {
                    @Override
                    public void onLiveProcessingStart(Closeable subscription) {
                    }

                    @Override
                    public void onEvent(Event event, Closeable subscription) {

                        String payload = event.data().data().value().utf8String();

                        LOG.info("Event {}", payload);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onClose() {
                    }
                },
                false,
                new UserCredentials("admin", "changeit"));
    }
}
