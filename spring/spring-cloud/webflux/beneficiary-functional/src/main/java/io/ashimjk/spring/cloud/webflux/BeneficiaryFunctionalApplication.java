package io.ashimjk.spring.cloud.webflux;

import io.ashimjk.spring.cloud.webflux.handler.BeneficiaryHandler;
import io.ashimjk.spring.cloud.webflux.model.Beneficiary;
import io.ashimjk.spring.cloud.webflux.repository.BeneficiaryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
public class BeneficiaryFunctionalApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeneficiaryFunctionalApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(ReactiveMongoOperations operations, BeneficiaryRepository repository) {
        return args -> {
            Flux<Beneficiary> beneficiaryFlux = Flux.just(
                    new Beneficiary(null, "ashim", "nepali"),
                    new Beneficiary(null, "kushal", "nepali")
            )
                    .flatMap(repository::save);

            beneficiaryFlux
                    .thenMany(repository.findAll())
                    .subscribe(System.out::println);

            /*
            operations
                    .collectionExists(Beneficiary.class)
                    .flatMap(exists -> exists ? operations.dropCollection(Beneficiary.class) : Mono.just(exists))
                    .thenMany(v -> operations.createCollection(Beneficiary.class))
                    .thenMany(beneficiaryFlux)
                    .thenMany(repository.findAll())
                    .subscribe(System.out::println);
            */
        };
    }

    @Bean
    public RouterFunction<ServerResponse> routes(BeneficiaryHandler handler) {
        //return route(GET("/beneficiary").and(accept(APPLICATION_JSON)), handler::getAllBeneficiary)
        //        .andRoute(POST("/beneficiary").and(contentType(APPLICATION_JSON)), handler::saveBeneficiary)
        //        .andRoute(DELETE("/beneficiary").and(accept(APPLICATION_JSON)), handler::deleteAllBeneficiaries)
        //        .andRoute(GET("/beneficiary/events").and(accept(TEXT_EVENT_STREAM)), handler::getBeneficiaryEvents)
        //        .andRoute(GET("/beneficiary/{id}").and(accept(APPLICATION_JSON)), handler::getBeneficiary)
        //        .andRoute(PUT("/beneficiary/{id}").and(contentType(APPLICATION_JSON)), handler::updateBeneficiary)
        //        .andRoute(DELETE("/beneficiary/{id}").and(accept(APPLICATION_JSON)), handler::deleteBeneficiary);

        return nest(path("/beneficiary"),
                nest(accept(APPLICATION_JSON).or(contentType(APPLICATION_JSON)).or(accept(TEXT_EVENT_STREAM)),
                        route(GET("/"), handler::getAllBeneficiary)
                                .andRoute(method(HttpMethod.POST), handler::saveBeneficiary)
                                .andRoute(DELETE("/"), handler::deleteAllBeneficiaries)
                                .andRoute(GET("/events"), handler::getBeneficiaryEvents)
                                .andNest(path("/{id}"),
                                        route(GET("/"), handler::getBeneficiary)
                                                .andRoute(PUT("/"), handler::updateBeneficiary)
                                                .andRoute(DELETE("/"), handler::deleteBeneficiary)
                                )
                )
        );
    }

}
