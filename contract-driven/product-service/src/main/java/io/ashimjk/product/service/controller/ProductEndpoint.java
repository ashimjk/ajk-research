package io.ashimjk.product.service.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ashimjk.product.contract.api.ProductApi;
import io.ashimjk.product.contract.api.ProductApiDelegate;
import io.ashimjk.product.contract.models.ProductListResponse;
import io.ashimjk.product.contract.models.ProductRequest;
import io.ashimjk.product.contract.models.ProductResponse;
import io.ashimjk.product.service.model.Product;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static io.ashimjk.product.service.mapper.RepresentationMapper.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class ProductEndpoint implements ProductApiDelegate {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final List<Product> products = new ArrayList<>();

    @SneakyThrows
    @PostConstruct
    public void setup() {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        readJson("json/product-response.json", ProductResponse.class);

        ProductListResponse response = readJson("json/product-list-response.json", ProductListResponse.class);
        products.addAll(toProducts(response));

        System.out.println(products);
    }

    @SneakyThrows
    private <T> T readJson(String path, Class<T> clazz) {
        String filePath = Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource(path)).getPath();

        List<String> lines = Files.readAllLines(Paths.get(filePath));

        String json = String.join("", lines);

        return objectMapper.readValue(json, clazz);
    }

    @Override
    public ResponseEntity<ProductResponse> createProduct(ProductRequest productRequest) {
        Product product = toProduct(productRequest);
        products.add(product);

        return ResponseEntity.created(linkTo(methodOn(ProductApi.class).getProductById(product.getId())).toUri())
                .body(toResponse(product));
    }

    @Override
    public ResponseEntity<ProductResponse> getProductById(Integer id) {
        Optional<Product> product = products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        return product.map(p -> ResponseEntity.ok(toResponse(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<ProductListResponse> getProducts(Integer page, Integer size) {
        return ResponseEntity.ok(toResponseList(products));
    }

}
