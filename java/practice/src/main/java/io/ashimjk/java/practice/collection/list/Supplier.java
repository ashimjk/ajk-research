package io.ashimjk.java.practice.collection.list;

import java.util.ArrayList;
import java.util.List;

public class Supplier {

    private final List<Product> products = new ArrayList<>();
    private String name;

    public Supplier(String name) {
        this.name = name;
    }

    List<Product> products() {
        return this.products;
    }

    String getName() {
        return this.name;
    }

}
