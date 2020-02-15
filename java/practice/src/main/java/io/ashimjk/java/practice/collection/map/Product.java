package io.ashimjk.java.practice.collection.map;

import lombok.Data;

import java.util.Comparator;

@Data
public class Product {

    public static final Comparator<Product> BY_WEIGHT = Comparator.comparing(Product::getWeight);
    public static final Comparator<Product> BY_NAME = Comparator.comparing(Product::getName);

    private int id;
    private String name;
    private Integer weight;

    public Product(int id, String name, Integer weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

}
