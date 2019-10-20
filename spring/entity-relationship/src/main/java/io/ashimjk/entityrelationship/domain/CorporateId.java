package io.ashimjk.entityrelationship.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Data
@NoArgsConstructor
@Embeddable
class CorporateId implements Serializable {

    @Column(name = "corpay_id")
    private String id;

}
