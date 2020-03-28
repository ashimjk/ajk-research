package io.ashimjk.spring.integration.service;

import io.ashimjk.spring.integration.domain.Beneficiary;
import io.ashimjk.spring.integration.repository.BeneficiaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Log4j2
@Component
@RequiredArgsConstructor
public class BeneficiaryService {

    private final BeneficiaryRepository repository;
    private final SseEmitters sseEmitters;

    @ServiceActivator(inputChannel = "dataPersist")
    public void saveBeneficiary(Beneficiary beneficiary) {
        log.info("beneficiary : {}", beneficiary);
        sseEmitters.publish(repository.save(beneficiary));
    }

    @ServiceActivator(inputChannel = "replyChannel")
    public void listBeneficiary(List<Beneficiary> beneficiaries) {
        beneficiaries.forEach(System.out::println);
    }

    public SseEmitter subscribe(String userId) {
        SseEmitter sseEmitter = sseEmitters.create(userId);
        streamInitialBeneficiary(userId);
        return sseEmitter;
    }

    private void streamInitialBeneficiary(String userId) {
        Sort sort = Sort.by(DESC, "timestamp");
        PageRequest pageRequest = PageRequest.of(0, 10, sort);
        List<Beneficiary> beneficiaries = repository.findAllByOrderByTimestamp(pageRequest);

        sseEmitters.publish(userId, beneficiaries);
    }

    public void unsubscribe(String userId) {
        sseEmitters.remove(userId);
    }

}
