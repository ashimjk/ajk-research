package io.ashimjk.entityrelationship.sample.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderItemKey;
    private String orderItemDesc;
    private Long orderItemQty;

//    public Long getOrderItemKey() {
//        return orderItemKey;
//    }
//
//    public void setOrderItemKey(Long orderItemKey) {
//        this.orderItemKey = orderItemKey;
//    }
//
//    public String getOrderItemDesc() {
//        return orderItemDesc;
//    }
//
//    public void setOrderItemDesc(String orderItemDesc) {
//        this.orderItemDesc = orderItemDesc;
//    }
//
//    public Long getOrderItemQty() {
//        return orderItemQty;
//    }
//
//    public void setOrderItemQty(Long orderItemQty) {
//        this.orderItemQty = orderItemQty;
//    }

}