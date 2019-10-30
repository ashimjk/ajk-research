package io.ashimjk.relationshipmapping.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import java.math.BigDecimal;

import static lombok.AccessLevel.PROTECTED;

@Data
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = PROTECTED)
public class PartTimeEmployee extends Employee {

    private BigDecimal hourlyWages;

    public PartTimeEmployee(String name, BigDecimal hourlyWages) {
        super(name);
        this.hourlyWages = hourlyWages;
    }

}
