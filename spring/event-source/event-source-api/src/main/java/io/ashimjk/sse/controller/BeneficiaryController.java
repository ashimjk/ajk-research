package io.ashimjk.sse.controller;

import io.ashimjk.sse.domain.Beneficiary;
import io.ashimjk.sse.repository.BeneficiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/beneficiary")
public class BeneficiaryController {

    private final List<SseEmitter> sseEmitters = Collections.synchronizedList(new ArrayList<>());
    private final BeneficiaryRepository beneficiaryRepository;
    private ExecutorService nonBlockingService = Executors.newCachedThreadPool();

    @PostMapping
    public Beneficiary create(@RequestBody @Valid Beneficiary beneficiary) {
        Beneficiary tempBeneficiary = this.beneficiaryRepository.save(beneficiary);
        synchronized (this.sseEmitters) {
            for (SseEmitter sseEmitter : this.sseEmitters) {
                try {
                    sseEmitter.send("created", MediaType.APPLICATION_JSON);
                    sseEmitter.complete();
                } catch (Exception e) {
                    //???
                }
            }
        }
        return tempBeneficiary;
    }

    @GetMapping
    public List<Beneficiary> list() {
        return this.beneficiaryRepository.findAll();
    }

    @GetMapping("/{id}")
    public Beneficiary get(@PathVariable("id") long id) {
        return this.beneficiaryRepository.findById(id).orElseGet(Beneficiary::new);
    }

    @PutMapping("/{id}")
    public Beneficiary update(@PathVariable("id") long id, @RequestBody @Valid Beneficiary beneficiary) {
        return beneficiaryRepository.save(beneficiary);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") long id) {
        this.beneficiaryRepository.deleteById(id);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

    @GetMapping(value = "subscribe")
    public SseEmitter handleSse() {
        SseEmitter emitter = new SseEmitter();
        nonBlockingService.execute(() -> {
            try {
                Beneficiary beneficiary = new Beneficiary(1L, "ashim", "nepali");
                emitter.send(beneficiary);
                emitter.complete();
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });
        return emitter;
    }

    public SseEmitter subscribe() {
        SseEmitter sseEmitter = new SseEmitter();
        synchronized (this.sseEmitters) {
            this.sseEmitters.add(sseEmitter);
            sseEmitter.onCompletion(() -> {
                synchronized (this.sseEmitters) {
                    this.sseEmitters.remove(sseEmitter);
                }
            });
            sseEmitter.onTimeout(sseEmitter::complete);
        }
        return sseEmitter;
    }

}
