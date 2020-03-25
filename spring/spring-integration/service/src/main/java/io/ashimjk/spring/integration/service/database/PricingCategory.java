package io.ashimjk.spring.integration.service.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "pricing_categories")
public class PricingCategory {

    @Id
    @Column(name = "pricing_category_code", unique = true, nullable = false, length = 1)
    private String code;

    @Column(name = "pricing_category_name", nullable = false, length = 20)
    private String name;

    @Column(name = "pricing_start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "pricing_end_date", nullable = false)
    private LocalDate endDate;

    public PricingCategory() {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return code + " " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PricingCategory that = (PricingCategory) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

}
