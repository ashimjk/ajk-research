# Event Store POC

The stream database written from the ground up for event sourcing.

## Module
- Order Service (Publisher) -- Uses Http Based
- Delivery Service (Subscriber) -- Uses Http Based
- Event Store Sample () -- Uses JVM Client

## Findings
- it uses Publish–subscribe pattern.
- its a stream database and opensource.
- provides client interface like Http Based and JVM Client.
- supports event sourcing.
- performance : 15,000 writes per second and 50,000 reads per second
- provides efficent admin-ui where we can view all the streams with their payload in the same order that were triggered.

## Setup
```bash
# runs eventstore server 
docker-compose up

# It opens 2113 port
# http://localhost:2113
# credentials: admin and changeit
```
## Summary
It uses Publish–subscribe pattern.

It provides two native support for Java Development, HTTP Based and JVM Client
Although it provides JVM Client, but using it via Spring Boot is of no use until now.
Instead HTTP based can be used.

Like in `event-store-sample`, it is unable to publish the event, when api request
is sent. It only works, when directly publish.

## Features

### Event Sourcing
It stores your data as a series of immutable events over time, making it easy to build
event-sourced applications.

### Open Source
Event Store is licensed under a 3-clause BSD license, whether it runs on a single node 
or as a high availability cluster. Commercial support services are available.

### High Availability
Event Store can run as a cluster of nodes containing the same data, which remains available 
for writes provided at least half the nodes are alive and connected.

### Client Interfaces
Event Store has a native `HTTP interface based` on the `AtomPub protocol` which is plenty fast 
enough for the majority of use cases. 

For high-performance use, there are native drivers for `.NET`, `Akka` and `Erlang`.

### Projections
Projections allow you to react to events as they are written, and to create new events when 
interesting combinations occur. You can use the same model for writing temporal correlation
queries that run over historical data and on into the future.

### Great Performance
Store at around 15,000 writes per second and 50,000 reads per second

## References
- [Event Sourcing](https://eventstore.org/docs/event-sourcing-basics/)
