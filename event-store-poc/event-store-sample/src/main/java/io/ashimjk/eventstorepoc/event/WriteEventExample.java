package io.ashimjk.eventstorepoc.event;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import eventstore.akka.tcp.ConnectionActor;
import eventstore.core.EsException;
import eventstore.core.EventData;
import eventstore.core.WriteEvents;
import eventstore.core.WriteEventsCompleted;
import eventstore.j.EventDataBuilder;
import eventstore.j.WriteEventsBuilder;

import java.util.UUID;

import static io.ashimjk.eventstorepoc.event.APIsExample.MY_STREAM;

public class WriteEventExample {

    public static void main(String[] args) {

        final ActorSystem system = ActorSystem.create();
        final ActorRef connection = system.actorOf(ConnectionActor.getProps());
        final ActorRef writeResult = system.actorOf(Props.create(WriteResult.class));

        final EventData event = new EventDataBuilder("my-event")
                .eventId(UUID.randomUUID())
                .data("my event data")
                .metadata("my first event")
                .build();

        final WriteEvents writeEvents = new WriteEventsBuilder(MY_STREAM)
                .addEvent(event)
                .expectAnyVersion()
                .build();

        connection.tell(writeEvents, writeResult);
    }

    public static class WriteResult extends AbstractActor {

        final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

        @Override
        public Receive createReceive() {
            return receiveBuilder()
                    .match(WriteEventsCompleted.class, m -> {
                        log.info("range: {}, position: {}", m.numbersRange(), m.position());
                        context().system().terminate();
                    })
                    .match(Status.Failure.class, f -> {
                        final EsException exception = (EsException) f.cause();
                        log.error(exception, exception.toString());
                    })
                    .build();
        }

    }
}
