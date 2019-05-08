package io.ashimjk.eventhandling;

import io.ashimjk.eventhandling.sync.CustomEventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class SpringEventHandlingApplication implements CommandLineRunner {

  private CustomEventPublisher publisher;

  public static void main(String[] args) {
    SpringApplication.run(SpringEventHandlingApplication.class, args);
  }

  @Override
  public void run(String... args) {

    publisher.doPublishEvent("Hello World");

    System.exit(0);

  }
}
