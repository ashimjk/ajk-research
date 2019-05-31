# Spring Event Handling Example

Spring allows us to create custom event, by default - ***spring events are synchronous***
- the event should extend `ApplicationEvent`
- the publisher should inject an `ApplicationEventPublisher` object
- the listener should implement the `ApplicationListener` interface
