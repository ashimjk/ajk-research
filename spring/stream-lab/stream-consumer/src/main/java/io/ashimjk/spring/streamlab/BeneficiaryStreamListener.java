package io.ashimjk.spring.streamlab;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class BeneficiaryStreamListener {

    @StreamListener(target = BeneficiaryStream.INPUT)
    public void createBeneficiary(Beneficiary beneficiary) {
        System.out.println("beneficiary = " + beneficiary);
    }

}
