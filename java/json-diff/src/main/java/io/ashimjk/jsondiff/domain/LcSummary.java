package io.ashimjk.jsondiff.domain;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public
class LcSummary {

    private String currentProcess;
    private String currentTask;
    private String status;

    private UserDecisionResource userDecision;

    private String expiryDate;
    private List<PaymentSchedule> paymentSchedule = null;

}
