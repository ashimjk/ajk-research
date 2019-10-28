package io.ashimjk.genericimpl.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
class IdType extends BaseEntity {

    private String name;
    private String number;
    private String document;
    private LocalDate expiryDate;

}
