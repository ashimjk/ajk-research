package io.ashimjk.spring.integration.poc.directchannel.model;

import lombok.Setter;

@Setter
public class Book {

    public enum Genre {
        FANTASY,
        HORROR,
        ROMANCE,
        THRILLER
    }

    private long bookId;
    private String title;
    private Genre genre;

    public long getBookId() {
        return bookId;
    }

    public String toString() {
        return String.format("Book %s, Genre: %s.", title, genre);
    }
}
