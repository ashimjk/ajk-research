package io.ashimjk.product.service;

import io.ashimjk.product.contract.api.ProductApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    public void test() {
        ProductApi productsApi;
    }

}
