# Contract Driven Development

## Product Server
- Contains three api:
    - list products
    - create product
    - get product by id

## Product Server Contract
Contains api provider implementation for server (producer).

## Product Client Contract
Contains api caller implementation for client (consumer).

## Run Dredd
```
dredd index.yaml http://192.168.0.100:8082/products/api/v1 --hookfiles=./hook.js
```

## Build Contract and Service
```
make build-contract
make build-service
```