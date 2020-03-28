package io.ashimjk.spring.integration.config;

import io.ashimjk.spring.integration.domain.Beneficiary;
import io.ashimjk.spring.integration.service.BeneficiaryGateway;
import io.ashimjk.spring.integration.service.BeneficiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
public class BeneficiaryController {

    private final BeneficiaryGateway beneficiaryGateway;
    private final BeneficiaryService beneficiaryService;

    @GetMapping("/beneficiary")
    public void sendRequest() {
        beneficiaryGateway.sendRequest(new Beneficiary());
    }

    @GetMapping("/beneficiary/subscribe/{userId}")
    public SseEmitter subscribe(@PathVariable String userId) {
        return beneficiaryService.subscribe(userId);
    }

    @GetMapping("/beneficiary/unsubscribe/{userId}")
    public void unsubscribe(@PathVariable String userId) {
        beneficiaryService.unsubscribe(userId);
    }

}
