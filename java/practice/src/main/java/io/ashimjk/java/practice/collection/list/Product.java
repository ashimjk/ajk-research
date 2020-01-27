package io.ashimjk.java.practice.collection.list;

import lombok.Data;

import java.util.Comparator;

@Data
public class Product {

    public static final Comparator<Product> BY_WEIGHT = Comparator.comparing(Product::getWeight);
    public static final Comparator<Product> BY_NAME = Comparator.comparing(Product::getName);

    private String name;
    private Integer weight;

    public Product(String name, Integer weight) {
        this.name = name;
        this.weight = weight;
    }

}
