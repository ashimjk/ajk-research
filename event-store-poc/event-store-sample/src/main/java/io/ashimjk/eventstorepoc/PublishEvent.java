package io.ashimjk.eventstorepoc;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;
import eventstore.akka.Settings;
import eventstore.akka.tcp.ConnectionActor;
import eventstore.core.EsException;
import eventstore.core.EventData;
import eventstore.core.WriteEvents;
import eventstore.core.WriteEventsCompleted;
import eventstore.j.EventDataBuilder;
import eventstore.j.SettingsBuilder;
import eventstore.j.WriteEventsBuilder;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.UUID;

@Component
public class PublishEvent {

    public static void main(String[] args) {
        DomainEvent domainEvent = new DomainEvent("123", "ashim");
        new PublishEvent().publish(domainEvent);
    }

    @SneakyThrows
    DomainEvent publish(DomainEvent domainEvent) {
        ObjectMapper mapper = new ObjectMapper();

        final ActorSystem system = ActorSystem.create();
        final Settings settings = new SettingsBuilder()
                .address(new InetSocketAddress("127.0.0.1", 1113))
                .defaultCredentials("admin", "changeit")
                .build();
        final ActorRef connection = system.actorOf(ConnectionActor.getProps(settings));

        final ActorRef writeResult = system.actorOf(Props.create(WriteResult.class));

        final EventData event = new EventDataBuilder("ajk-event-type")
                .eventId(UUID.randomUUID())
                .jsonData(mapper.writeValueAsString(domainEvent))
                .build();

        final WriteEvents writeEvents = new WriteEventsBuilder("ajk-event-store-poc")
                .addEvent(event)
                .expectAnyVersion()
                .build();

        connection.tell(writeEvents, writeResult);

        return domainEvent;
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
