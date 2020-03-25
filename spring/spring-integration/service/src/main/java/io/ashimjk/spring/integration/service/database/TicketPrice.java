package io.ashimjk.spring.integration.service.database;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "ticket_prices")
@SequenceGenerator(name = "ticket_prices_gen", sequenceName = "ticket_prices_seq", allocationSize = 1)
public class TicketPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_prices_gen")
    @Column(name = "ticket_price_id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ticket_type_code", nullable = false)
    private TicketType ticketType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pricing_category_code", nullable = false)
    private PricingCategory pricingCategory;

    @Column(name = "base_price", nullable = false, precision = 8, scale = 2)
    private BigDecimal basePrice;

    public TicketPrice() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public PricingCategory getPricingCategory() {
        return pricingCategory;
    }

    public void setPricingCategory(PricingCategory pricingCategory) {
        this.pricingCategory = pricingCategory;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketPrice that = (TicketPrice) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
