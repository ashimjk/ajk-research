package io.ashimjk.eventstorepoc.event;

import akka.actor.ActorSystem;
import eventstore.akka.EventStoreExtension;
import eventstore.core.settings.PersistentSubscriptionSettings;
import eventstore.j.EsConnection;
import eventstore.j.PersistentSubscriptionSettingsBuilder;

import static io.ashimjk.eventstorepoc.event.APIsExample.MY_STREAM;

public class PersistentSubscriptionExample {
    public static void main(String[] args) {
        final ActorSystem system = ActorSystem.create();
        final EventStoreExtension extension = EventStoreExtension.get(system);
        final EsConnection connection = extension.connectionJava();

        connection.createPersistentSubscription(MY_STREAM, "example", null, null);

        final PersistentSubscriptionSettings settings = new PersistentSubscriptionSettingsBuilder()
                .startFromCurrent()
                .roundRobin()
                .doNotResolveLinkTos()
                .withExtraStatistic()
                .build();

        connection.updatePersistentSubscription(MY_STREAM, "example", settings, null);

        connection.deletePersistentSubscription(MY_STREAM, "example", null);
    }
}
