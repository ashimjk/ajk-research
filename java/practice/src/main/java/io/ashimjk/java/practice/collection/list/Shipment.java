package io.ashimjk.java.practice.collection.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Shipment implements Iterable<Product> {

    private static final int LIGHT_VAN_MAX_WEIGHT = 20;
    public static final int PRODUCT_NOT_PRESENT = -1;

    private final List<Product> products = new ArrayList<>();
    private List<Product> lightVanProducts;
    private List<Product> heavyVanProducts;

    void add(Product product) {
        this.products.add(product);
    }

    void replace(Product oldProduct, Product newProduct) {
        final int index = this.products.indexOf(oldProduct);
        if (index != PRODUCT_NOT_PRESENT) {
            this.products.set(index, newProduct);
        }
    }

    @Override
    public Iterator<Product> iterator() {
        return this.products.iterator();
    }

    public void prepare() {
        // sort our list of products by weight
        // Collections.sort(products, Product.BY_WEIGHT);
        this.products.sort(Product.BY_WEIGHT);

        // find the product index that needs the heavy van
        int splitPoint = this.findSplitPoint();

        // assign views of the product list for heavy and light vans
        this.lightVanProducts = this.products.subList(0, splitPoint);
        this.heavyVanProducts = this.products.subList(splitPoint, this.products.size());
    }

    private int findSplitPoint() {
        for (int i = 0; i < this.products.size(); i++) {
            final Product product = this.products.get(i);
            if (product.getWeight() > LIGHT_VAN_MAX_WEIGHT) {
                return i;
            }
        }

        return 0;
    }

    public List<Product> getLightVanProducts() {
        return this.lightVanProducts;
    }

    public List<Product> getHeavyVanProducts() {
        return this.heavyVanProducts;
    }

}
