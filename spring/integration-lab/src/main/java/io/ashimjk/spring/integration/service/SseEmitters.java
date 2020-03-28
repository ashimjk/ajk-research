package io.ashimjk.spring.integration.service;

import io.ashimjk.spring.integration.domain.Beneficiary;
import io.ashimjk.spring.integration.exception.FailedToPublishException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class SseEmitters {

    private final Map<String, SseEmitter> sseOfUserId = new ConcurrentHashMap<>();
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public SseEmitter create(String userId) {
        closeEmitterIfPresent(userId);
        SseEmitter emitter = new SseEmitter(-1L);
        this.sseOfUserId.put(userId, emitter);
        return emitter;
    }

    public void remove(String userId) {
        closeEmitterIfPresent(userId);
    }

    private void closeEmitterIfPresent(String userId) {
        sseOfUserId.computeIfPresent(userId, (key, sseEmitter) -> {
            sseEmitter.complete();
            return null;
        });
    }

    public void publish(String userId, List<Beneficiary> beneficiaries) {
        executor.execute(() ->
                beneficiaries.forEach(beneficiary -> {
                    try {
                        sseOfUserId.get(userId).send(beneficiary);
                    } catch (IOException e) {
                        closeEmitterIfPresent(userId);
                        throw new FailedToPublishException(userId);
                    }
                })
        );
    }

    public void publish(Beneficiary beneficiary) {
        executor.execute(() -> {
            List<String> deadEmitters = new ArrayList<>();
            sseOfUserId.forEach((userId, sseEmitter) -> {
                try {
                    sseEmitter.send(beneficiary);
                } catch (IOException e) {
                    deadEmitters.add(userId);
                }
            });

            deadEmitters.forEach(this::closeEmitterIfPresent);
        });
    }

}
