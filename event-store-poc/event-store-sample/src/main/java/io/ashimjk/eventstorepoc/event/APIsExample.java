package io.ashimjk.eventstorepoc.event;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import eventstore.akka.tcp.ConnectionActor;
import eventstore.core.Event;
import eventstore.core.EventNumber;
import eventstore.core.ReadEvent;
import eventstore.j.EsConnection;
import eventstore.j.EsConnectionFactory;
import eventstore.j.ReadEventBuilder;
import scala.concurrent.Future;

public class APIsExample {
    static final String MY_STREAM = "my-stream";
    private final ActorSystem system = ActorSystem.create();

    public void methodCall() {
        final EsConnection connection = EsConnectionFactory.create(system);
        final Future<Event> future = connection.readEvent(MY_STREAM, new EventNumber.Exact(0), false, null);
    }

    public void messageSending() {
        final ActorRef connection = system.actorOf(ConnectionActor.getProps());
        final ReadEvent readEvent = new ReadEventBuilder(MY_STREAM)
                .first()
                .build();
        connection.tell(readEvent, null);
    }

}
