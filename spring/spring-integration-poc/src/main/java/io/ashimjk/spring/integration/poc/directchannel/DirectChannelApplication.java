package io.ashimjk.spring.integration.poc.directchannel;

import io.ashimjk.spring.integration.poc.directchannel.incoming.BookPublisher;
import io.ashimjk.spring.integration.poc.directchannel.model.Book;
import io.ashimjk.spring.integration.poc.directchannel.pub.Librarian;
import io.ashimjk.spring.integration.poc.directchannel.sub.PremiumReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;

import java.util.List;

@SpringBootApplication
public class DirectChannelApplication {

    @Autowired
    private BookPublisher bookPublisher;

    @Autowired
    private Librarian librarian;

    @Autowired
    private DirectChannel directChannel;

    @Bean
    public DirectChannel directChannel() {
        return new DirectChannel();
    }

    public static void main(String[] args) {
        SpringApplication.run(DirectChannelApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            directChannel.subscribe(new PremiumReader());

            List<Book> books = bookPublisher.getBooks();

            for (Book book : books) {
                librarian.sendPremiumReaders(book);
            }

            System.exit(SpringApplication.exit(ctx));
        };
    }
}
