package io.ashimjk.keycloak.controller;

import io.ashimjk.keycloak.model.Book;
import io.ashimjk.keycloak.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ApiBasedController {

    private final BookRepository bookRepository;

    @GetMapping(value = "/")
    public Map<String, String> getHome() {
        return Collections.singletonMap("resource", "API - Based");
    }

    @GetMapping(value = "/books")
    public List<Book> getBooks() {
        return bookRepository.readAll();
    }

    @PostMapping(value = "/books")
    public List<Book> getPostedBooks() {
        return bookRepository.readAll();
    }

    @GetMapping(value = "/manage")
    public List<Book> getManager() {
        return bookRepository.readAll();
    }

}
