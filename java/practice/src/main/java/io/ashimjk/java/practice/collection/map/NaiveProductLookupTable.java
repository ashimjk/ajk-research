package io.ashimjk.java.practice.collection.map;

import java.util.ArrayList;
import java.util.List;

public class NaiveProductLookupTable implements ProductLookupTable {

    private List<Product> products = new ArrayList<>();

    @Override
    public Product lookupById(final int id) {
        for (Product product : this.products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void addProduct(final Product productToAdd) {
        for (Product product : this.products) {
            if (product.getId() == productToAdd.getId()) {
                throw new IllegalArgumentException("Unable to add product, duplicate id for " + productToAdd);
            }
        }

        this.products.add(productToAdd);
    }

    @Override
    public void clear() {
        this.products.clear();
    }

}
