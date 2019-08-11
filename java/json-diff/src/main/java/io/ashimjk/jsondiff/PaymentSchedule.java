package io.ashimjk.jsondiff;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
class PaymentSchedule {
    private String paymentType;
    private BigDecimal percentage;
    private Integer numberOfDays;
    private String afterIncident;
}
