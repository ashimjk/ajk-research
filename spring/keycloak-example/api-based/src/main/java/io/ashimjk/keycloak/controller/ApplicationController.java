package io.ashimjk.keycloak.controller;

import io.ashimjk.keycloak.model.Book;
import io.ashimjk.keycloak.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ApplicationController {

    private final BookRepository bookRepository;

    @GetMapping(value = "/")
    public String getHome() {
        return "API - Based";
    }

    @GetMapping(value = "/books")
    public List<Book> getBooks() {
        return bookRepository.readAll();
    }

    @GetMapping(value = "/manager")
    public List<Book> getManager() {
        return bookRepository.readAll();
    }

    @GetMapping(value = "/logout")
    public void logout(HttpServletRequest request) throws ServletException {
        request.logout();
    }

}
