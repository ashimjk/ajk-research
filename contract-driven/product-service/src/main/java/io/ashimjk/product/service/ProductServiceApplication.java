package io.ashimjk.product.service;

import io.ashimjk.product.contract.api.ProductApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "io.ashimjk.product")
@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    public void test() {
        ProductApi productsApi;
    }

}
