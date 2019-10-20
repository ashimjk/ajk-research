package io.ashimjk.entityrelationship.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@EqualsAndHashCode
@Entity
public class ShareHolder extends BaseEntity {

    private String fullName;
    private String nationalId;

}
