package io.ashimjk.eventstorepoc.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.util.UUID;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class ItemOrder extends AbstractAggregateRoot<ItemOrder> {

    @javax.persistence.Id
    @Id
    private String id;
    @Embedded
    private Name customerName;
    @Embedded
    private DeliveryAddress deliveryAddress;

    private ItemOrder(Name customerName, DeliveryAddress deliveryAddress) {
        this.customerName = customerName;
        this.deliveryAddress = deliveryAddress;
        this.id = UUID.randomUUID().toString();
    }

    public static ItemOrder create(Name name, DeliveryAddress address) {
        return new ItemOrder(name, address);
    }

    public void place() {
        registerEvent(new OrderPlaced(this.getId(), this.getCustomerName(), this.getDeliveryAddress()));
    }
}
