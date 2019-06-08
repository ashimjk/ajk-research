package io.ashimjk.greetings.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@FeignClient("greetings-service")
interface GreetingsClient {

    @GetMapping("/greetings/{name}")
    Greeting greet(@PathVariable String name);

}

@EnableCircuitBreaker
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class GreetingsClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(GreetingsClientApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}

@RestController
class GreetingsApiGatewayRestController {

    private final RestTemplate restTemplate;
    private final GreetingsClient greetingsClient;
    private final DiscoveryClient discoveryClient;

    GreetingsApiGatewayRestController(RestTemplate restTemplate, GreetingsClient greetingsClient, DiscoveryClient discoveryClient) {
        this.restTemplate = restTemplate;
        this.greetingsClient = greetingsClient;
        this.discoveryClient = discoveryClient;
    }

    public String fallback(String name) {
        return "oops! something went wrong...";
    }

    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/feign/{name}")
    String feign(@PathVariable String name) {
        return greetingsClient
                .greet(name)
                .getGreeting();
    }

    @GetMapping("/hi/{name}")
    String greet(@PathVariable String name) {
        // hystrix circuit breaker imperative impl
        if (this.discoveryClient.getInstances("greetings-service").size() > 0) {
            ResponseEntity<Greeting> exchange = this.restTemplate.exchange("http://greetings-service/greetings/{name}",
                    HttpMethod.GET, null, Greeting.class, name);

            return exchange
                    .getBody()
                    .getGreeting();
        }

        return null;
    }

}

class Greeting {

    private String greeting;

    String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

}