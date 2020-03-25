package io.ashimjk.spring.integration.service.database;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "discount_codes")
@SequenceGenerator(name = "discount_codes_gen", sequenceName = "discount_codes_seq", allocationSize = 1)
public class DiscountCode {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discount_codes_gen")
    @Column(name = "discount_code_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "discount_code", nullable = false, length = 20)
    private String code;

    @Column(name = "discount_name", nullable = false, length = 30)
    private String name;

    @Column(name = "discount_type", nullable = false, length = 1)
    private String type;

    @Column(name = "discount_amount", nullable = false, precision = 8, scale = 2)
    private BigDecimal amount;

    public DiscountCode() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return id + " " + code + " " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountCode that = (DiscountCode) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
