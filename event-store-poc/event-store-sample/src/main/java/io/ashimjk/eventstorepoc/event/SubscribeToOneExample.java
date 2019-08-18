package io.ashimjk.eventstorepoc.event;

import akka.actor.ActorSystem;
import eventstore.akka.SubscriptionObserver;
import eventstore.core.Event;
import eventstore.j.EsConnection;
import eventstore.j.EsConnectionFactory;

import java.io.Closeable;

import static io.ashimjk.eventstorepoc.event.APIsExample.MY_STREAM;

public class SubscribeToOneExample {
    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create();
        final EsConnection connection = EsConnectionFactory.create(system);
        final Closeable closeable = connection.subscribeToStream(MY_STREAM,
                new SubscriptionObserver<Event>() {
                    @Override
                    public void onLiveProcessingStart(Closeable subscription) {
                        system.log().info("live processing started");
                    }

                    @Override
                    public void onEvent(Event event, Closeable subscription) {
                        system.log().info(event.data().data().value().utf8String());
                    }

                    @Override
                    public void onError(Throwable e) {
                        system.log().error(e.toString());
                    }

                    @Override
                    public void onClose() {
                        system.log().error("subscription closed");
                    }
                }, false, null);
    }
}
