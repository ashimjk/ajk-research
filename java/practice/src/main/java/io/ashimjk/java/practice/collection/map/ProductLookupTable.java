package io.ashimjk.java.practice.collection.map;

public interface ProductLookupTable {

    Product lookupById(int id);

    void addProduct(Product productToAdd);

    void clear();

}
