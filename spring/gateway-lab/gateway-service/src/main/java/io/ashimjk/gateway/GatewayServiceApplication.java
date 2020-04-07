package io.ashimjk.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

    @Bean
    public GlobalFilter customFilter() {
        return new GatewayServiceFilter();
    }

    //@Bean
    //public ServerCodecConfigurer serverCodecConfigurer() {
    //    return ServerCodecConfigurer.create();
    //}

    public static class GatewayServiceFilter implements GlobalFilter, Ordered {

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            HttpHeaders headers = exchange.getResponse().getHeaders();

            headers.set("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate");
            headers.set("Pragma", "no-cache");
            headers.set("X-Content-Type-Options", "nosniff");
            headers.set("Strict-Transport-Security", "max-age=31536000 ; includeSubDomains");
            headers.set("X-Frame-Options", "DENY");
            headers.set("X-XSS-Protection", "1; mode=block");

            return chain.filter(exchange);
        }

        @Override
        public int getOrder() {
            return -1;
        }

    }

}
