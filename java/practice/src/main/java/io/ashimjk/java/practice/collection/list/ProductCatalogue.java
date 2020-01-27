package io.ashimjk.java.practice.collection.list;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import static io.ashimjk.java.practice.collection.list.Product.BY_WEIGHT;

public class ProductCatalogue implements Iterable<Product> {

    private static final int LIGHT_VAN_MAX_WEIGHT = 20;

    SortedSet<Product> products = new TreeSet<>(BY_WEIGHT);

    public void isSuppliedBy(Supplier supplier) {
        this.products.addAll(supplier.products());
    }

    @Override
    public Iterator<Product> iterator() {
        return this.products.iterator();
    }

    public SortedSet<Product> lightVanProducts() {
        final Product product = this.findHeaviestVanProduct();
        return this.products.headSet(product);
    }

    public SortedSet<Product> heavyVanProducts() {
        final Product product = this.findHeaviestVanProduct();
        return this.products.tailSet(product);
    }

    private Product findHeaviestVanProduct() {
        for (Product product : this.products) {
            if (product.getWeight() > LIGHT_VAN_MAX_WEIGHT) {
                return product;
            }
        }

        return this.products.last();
    }

}
