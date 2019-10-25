package io.ashimjk.entityrelationship.sample.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderKey;
    private String orderNbr;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "productorder_id")
    private List<OrderItem> orderItems = new ArrayList<>();

//    public Long getOrderKey() {
//        return orderKey;
//    }
//
//    public void setOrderKey(Long orderKey) {
//        this.orderKey = orderKey;
//    }
//
//    public String getOrderNbr() {
//        return orderNbr;
//    }
//
//    public void setOrderNbr(String orderNbr) {
//        this.orderNbr = orderNbr;
//    }
//
//    public void addOrderItem(String desc, Long qty) {
//
//        OrderItem orderItem = new OrderItem();
//        orderItem.setOrderItemDesc(desc);
//        orderItem.setOrderItemQty(qty);
//
//        List<OrderItem> orderItems = getOrderItems();
//        if (orderItems == null) {
//            orderItems = new ArrayList<>();
//            setOrderItems(orderItems);
//        }
//
//        orderItems.add(orderItem);
////        orderItem.setOrderItemOrder(this);
//    }
//
//    public List<OrderItem> getOrderItems() {
//        return orderItems;
//    }
//
//    public void setOrderItems(List<OrderItem> orderItems) {
//        this.orderItems = orderItems;
//    }

}
