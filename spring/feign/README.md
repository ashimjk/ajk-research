# Feign

## Configuration
Feign client is composed of a set of customizable components.
The above class contains these beans:
- **Decoder** – ResponseEntityDecoder, which wraps SpringDecoder, used to decode the Response
- **Encoder** – SpringEncoder, used to encode the RequestBody
- **Logger** – Slf4jLogger is the default logger used by Feign
- **Contract** – SpringMvcContract, which provides annotation processing
- **Feign-Builder** – HystrixFeign.Builder used to construct the components
- **Client** – LoadBalancerFeignClient or default Feign client

## Logging
- **NONE** – no logging, which is the default
- **BASIC** – log only the request method, URL, and response status
- **HEADERS** – log the basic information together with request and response headers
- **FULL** – log the body, headers, and metadata for both request and response