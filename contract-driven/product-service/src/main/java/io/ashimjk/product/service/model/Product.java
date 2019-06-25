package io.ashimjk.product.service.model;

import io.ashimjk.product.contract.models.ProductResponse.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Product {

    private Integer id;
    private BigDecimal price;
    private StatusEnum status;

}
