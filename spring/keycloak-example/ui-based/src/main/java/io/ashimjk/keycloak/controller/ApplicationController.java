package io.ashimjk.keycloak.controller;

import io.ashimjk.keycloak.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class ApplicationController {

    private final HttpServletRequest request;
    private final BookRepository bookRepository;

    @GetMapping(value = "/")
    public String getHome() {
        return "index";
    }

    @GetMapping(value = "/books")
    public String getBooks(Model model) {
        configCommonAttributes(model);
        model.addAttribute("books", bookRepository.readAll());
        return "books";
    }

    private void configCommonAttributes(Model model) {
        model.addAttribute("name", getKeycloakSecurityContext().getIdToken().getGivenName());
    }

    private KeycloakSecurityContext getKeycloakSecurityContext() {
        return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
    }

    @GetMapping(value = "/manager")
    public String getManager(Model model) {
        configCommonAttributes(model);
        model.addAttribute("books", bookRepository.readAll());
        return "manager";
    }

    @GetMapping(value = "/logout")
    public String logout() throws ServletException {
        request.logout();
        return "redirect:/";
    }

}
