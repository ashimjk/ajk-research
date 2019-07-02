package io.ashimjk.product.service.mapper;

import io.ashimjk.product.contract.models.ProductListResponse;
import io.ashimjk.product.contract.models.ProductRequest;
import io.ashimjk.product.contract.models.ProductResponse;
import io.ashimjk.product.service.model.Product;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class RepresentationMapper {

    public static ProductListResponse toResponseList(List<Product> products) {
        BigDecimal totalPrice = new BigDecimal(0);
        List<ProductResponse> responses = Optional.ofNullable(products)
                .orElse(Collections.emptyList())
                .stream()
                .peek(p -> totalPrice.add(p.getPrice()))
                .map(RepresentationMapper::toResponse)
                .collect(Collectors.toList());

        return new ProductListResponse()
                .products(responses)
                .total(totalPrice);
    }

    public static ProductResponse toResponse(Product product) {
        return new ProductResponse()
                .id(product.getId())
                .price(product.getPrice())
                .status(product.getStatus());
    }

    public static List<Product> toProducts(ProductListResponse response) {
        return response.getProducts()
                .stream()
                .map(RepresentationMapper::toProduct)
                .collect(Collectors.toList());
    }

    private static Product toProduct(ProductResponse response) {
        return Product.builder()
                .id(response.getId())
                .price(response.getPrice())
                .status(response.getStatus())
                .build();
    }

    public static Product toProduct(ProductRequest productRequest) {
        Random random = new Random();
        return Product.builder()
                .id(random.nextInt())
                .price(productRequest.getPrice())
                .status(ProductResponse.StatusEnum.CREATED)
                .build();
    }

}
