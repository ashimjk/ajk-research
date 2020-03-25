package io.ashimjk.spring.integration.service.database;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "attendee_tickets")
@SequenceGenerator(name = "attendee_tickets_gen", sequenceName = "attendee_tickets_seq", allocationSize = 1)
public class AttendeeTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attendee_tickets_gen")
    @Column(name = "attendee_ticket_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "ticket_code", unique = true, nullable = false, updatable = false, length = 40)
    private String ticketCode;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false, orphanRemoval = true)
    @JoinColumn(name = "attendee_id", unique = true, nullable = false)
    private Attendee attendee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ticket_price_id", nullable = false)
    private TicketPrice ticketPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discount_code_id")
    private DiscountCode discountCode;

    @Column(name = "net_price", nullable = false, precision = 8, scale = 2)
    private BigDecimal netPrice;

    public AttendeeTicket() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(String ticketCode) {
        this.ticketCode = ticketCode;
    }

    public Attendee getAttendee() {
        return attendee;
    }

    public void setAttendee(Attendee attendee) {
        this.attendee = attendee;
    }

    public TicketPrice getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(TicketPrice ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public DiscountCode getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(DiscountCode discountCode) {
        this.discountCode = discountCode;
    }

    public BigDecimal getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(BigDecimal netPrice) {
        this.netPrice = netPrice;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttendeeTicket that = (AttendeeTicket) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
