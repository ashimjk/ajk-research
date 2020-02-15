package io.ashimjk.java.practice.collection.map;

import java.util.HashMap;
import java.util.Map;

public class MapProductLookupTable implements ProductLookupTable {

    private Map<Integer, Product> idToProduct = new HashMap<>();

    @Override
    public Product lookupById(final int id) {
        return this.idToProduct.get(id);
    }

    @Override
    public void addProduct(final Product productToAdd) {
        if (this.idToProduct.containsKey(productToAdd.getId())) {
            throw new IllegalArgumentException("Unable to add product, duplicate id for " + productToAdd);
        }
        this.idToProduct.put(productToAdd.getId(), productToAdd);
    }

    @Override
    public void clear() {
        this.idToProduct.clear();
    }

}
