package io.ashimjk.entityrelationship.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
class ShareHolder extends BaseEntity {

    private String fullName;
    private String nationalId;

}
