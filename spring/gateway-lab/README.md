# Gateway Lab

## Cors Request
```shell script
curl -H "Origin: http://example.com" \
    -H "Access-Control-Request-Method: POST" \
    -H "Access-Control-Request-Headers: X-Requested-With" \
    -X OPTIONS --verbose \
    http://localhost:8080/beneficiary/service
```

## Diagram
![](gateway.jpeg)
