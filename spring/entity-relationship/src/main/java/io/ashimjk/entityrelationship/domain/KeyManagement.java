package io.ashimjk.entityrelationship.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class KeyManagement extends BaseEntity {

    private String fullName;
    private String title;
    private String nationalNumber;

}
