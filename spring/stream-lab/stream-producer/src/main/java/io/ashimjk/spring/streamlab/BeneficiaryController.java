package io.ashimjk.spring.streamlab;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/beneficiary")
public class BeneficiaryController {

    private final BeneficiaryCreateSource source;

    @PostMapping
    public void createBeneficiary(@RequestBody Beneficiary beneficiary) {
        source.createBeneficiary().send(MessageBuilder.withPayload(beneficiary).build());
    }

}
