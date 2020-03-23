package io.ashimjk.spring.cloud.webflux.handler;

import io.ashimjk.spring.cloud.webflux.model.Beneficiary;
import io.ashimjk.spring.cloud.webflux.model.BeneficiaryEvent;
import io.ashimjk.spring.cloud.webflux.repository.BeneficiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Component
@RequiredArgsConstructor
public class BeneficiaryHandler {

    private final BeneficiaryRepository repository;

    public Mono<ServerResponse> getAllBeneficiary(ServerRequest request) {
        Flux<Beneficiary> beneficiaries = repository.findAll();

        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(beneficiaries, Beneficiary.class);
    }

    public Mono<ServerResponse> getBeneficiary(ServerRequest request) {
        String id = request.pathVariable("id");

        Mono<Beneficiary> beneficiaryMono = repository.findById(id);
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        return beneficiaryMono
                .flatMap(beneficiary ->
                        ServerResponse.ok()
                                .contentType(APPLICATION_JSON)
                                .body(fromValue(beneficiary))
                )
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> saveBeneficiary(ServerRequest request) {
        Mono<Beneficiary> beneficiaryMono = request.bodyToMono(Beneficiary.class)
                .log();

        return beneficiaryMono.flatMap(beneficiary ->
                ServerResponse.status(HttpStatus.CREATED)
                        .contentType(APPLICATION_JSON)
                        .body(repository.save(beneficiary), Beneficiary.class));
    }

    public Mono<ServerResponse> updateBeneficiary(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<Beneficiary> beneficiaryMono = request.bodyToMono(Beneficiary.class);
        Mono<Beneficiary> existingBeneficiaryMono = repository.findById(id);

        Mono<ServerResponse> notFoundMono = ServerResponse.notFound().build();

        return beneficiaryMono.zipWith(existingBeneficiaryMono,
                (beneficiary, existingBeneficiary) ->
                        new Beneficiary(existingBeneficiary.getId(), beneficiary.getFullName(), beneficiary.getNationality())
        )
                .flatMap(beneficiary ->
                        ServerResponse.ok()
                                .contentType(APPLICATION_JSON)
                                .body(repository.save(beneficiary), Beneficiary.class))
                .switchIfEmpty(notFoundMono);
    }

    public Mono<ServerResponse> deleteBeneficiary(ServerRequest request) {
        String id = request.pathVariable("id");

        Mono<Beneficiary> beneficiaryMono = repository.findById(id);
        Mono<ServerResponse> notFoundMono = ServerResponse.notFound().build();

        return beneficiaryMono
                .flatMap(beneficiary ->
                        ServerResponse.ok()
                                .build(repository.delete(beneficiary)))
                .switchIfEmpty(notFoundMono);
    }

    public Mono<ServerResponse> deleteAllBeneficiaries(ServerRequest request) {
        return ServerResponse.ok().build(repository.deleteAll());
    }

    public Mono<ServerResponse> getBeneficiaryEvents(ServerRequest request) {
        Flux<BeneficiaryEvent> eventsFlux = Flux.interval(Duration.ofSeconds(1))
                .map(val -> new BeneficiaryEvent(val, "Beneficiary Event"));

        return ServerResponse.ok()
                .contentType(TEXT_EVENT_STREAM)
                .body(eventsFlux, Beneficiary.class);
    }

}
