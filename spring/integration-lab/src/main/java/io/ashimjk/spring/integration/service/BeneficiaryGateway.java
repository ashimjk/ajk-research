package io.ashimjk.spring.integration.service;

import io.ashimjk.spring.integration.domain.Beneficiary;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface BeneficiaryGateway {

    @Gateway(requestChannel = "requestChannel", payloadExpression = "{}")
    void sendRequest(Beneficiary beneficiary);

}
