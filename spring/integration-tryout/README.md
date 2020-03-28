# Spring Integration - Tryout

## MessagingGateway, Gateway, Service Activator 
- Message - Message is a generic wrapper for any Java object. It has the header and the payload (your actual object)
- Message Channel - it represents a pipe. messages are sent and received to and from a channel
- @EnableIntegration - is useful when you have multiple config files in your application along with spring integration configurations
- @IntegrationComponentScan - used for class path scaling just like @ComponentScan, but restricted to integration components where @ComponentScan cannot reach
- @MessagingGateway - it is an interface that serves as a proxy abstraction over the messaging structure
- @Gateway is usually annotated on the method signature inside the interface
- DirectChannel - acts like a point to point channel and is the simplest
- Service Activator - acts like a connector between the spring managed object and the input channel

## Transformer
- Transformer – converts the payload or structure to another form (like for example XML payload to JSON)

There are multiple built in transformers – xml to object (unmarshaller) / object to xml (marshaller),
Object to string, File to string, Object serializer/deserializer, Object to map, Object to Json

## Router
Routers consume Messages from a Message Channel and forward each consumed message to one or more different 
Message Channel depending on a set of conditions.

We have a payload type router, header value router, recipient list router etc available. 

## Filter
Message Filters are used to decide whether a Message should be passed along or dropped based on some criteria such as 
a Message Header value or Message content itself.
