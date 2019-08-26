package io.ashimjk.spring.integration.poc.directchannel.incoming;

import io.ashimjk.spring.integration.poc.directchannel.model.Book;
import io.ashimjk.spring.integration.poc.directchannel.model.Book.Genre;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * initiates the message flow
 */
@Component
public class BookPublisher {
    private long nextBookId;

    public BookPublisher() {
        this.nextBookId = 1001L;
    }

    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();

        books.add(createFantasyBook());
        books.add(createHorrorBook());
        books.add(createRomanceBook());
        books.add(createThrillerBook());

        return books;
    }

    private Book createBook(Genre genre) {
        Book book = new Book();
        book.setBookId(nextBookId++);

        String title = "# " + book.getBookId();
        book.setTitle(title);
        book.setGenre(genre);

        return book;
    }

    private Book createFantasyBook() {
        return createBook(Genre.FANTASY);
    }

    private Book createHorrorBook() {
        return createBook(Genre.HORROR);
    }

    private Book createRomanceBook() {
        return createBook(Genre.ROMANCE);
    }

    private Book createThrillerBook() {
        return createBook(Genre.THRILLER);
    }
}
