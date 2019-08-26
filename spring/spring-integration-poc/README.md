# Spring Integration POC

## Cargo Message Processing
Cargo messaging implementation shows basic message endpoints’ behaviours for understanding easily. 
Cargo messaging system listens cargo messages from external system by using a CargoGateway Interface. 
Received cargo messages are processed by using `CargoSplitter`, `CargoFilter`, `CargoRouter`,
`CargoTransformer` MessageEndpoints. After then, processed successful domestic and
international cargo messages are sent to `CargoServiceActivator`.

### Message Flow
- Gateway (starting point )
- Splitter (extract payload)
- Filter (filter weight limit)
    - Router (delegate to different route based on shipping type)
    - Service Activator (Discarded Message)
- Transformer (converts cargo into its respective cargo (domestic or international))
- Service Activator (final endpoint for flow)
