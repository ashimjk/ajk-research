# Edge Services and Routing

## Edge Service
- Sits at the edge of the architecture
- It is client specific.
- Clients are browser, android, swift (anything that have IP Addresses)
- The first port of calls for clients calling the system
- Allows client needs to be dealt with:
    - Payload
    - Protocol
    - Security
    - Rate Limiting
- Registries (eureka)

It is a intermediary between outside world client and the downstream services,
it is an ideal place or an enviable place to do following things
- authentication
- authorization
- proxying (using zuul filter)
- rate limiting (using zuul filter)
- routing (using zuul as api gateway)
- take care of error handling (using hystrix circuit breaker)
- to do any kind of translations or transformations for payload that is
going back and forth like transforming protocol to downstream services
- can use adapter design pattern for any client specific requirement (can use openfiegn) 
- best place to handle cors by proxying the request


## Eureka Server
By convention but not requirement, all of the eureka application
spin up in port : 8761.

> http://localhost:8761/

## Greeting Service
It is eureka client, where it register itself in eureka server using
`@EnableDiscoveryClient` annotation.

We can use `eureka.client.service-url.defaultZone = http://localhost:8761/eureka` property to define
eureka server detail.

By default it listens to localhost:8761 port, so we don't need to provide any properties.

## Greetings Client
- API Gateway
- Edge Service