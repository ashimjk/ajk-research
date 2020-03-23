package io.ashimjk.spring.cloud.webflux.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiaryEvent {

    private Long eventId;
    private String eventType;

}
