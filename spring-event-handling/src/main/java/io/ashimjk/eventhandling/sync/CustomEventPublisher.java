package io.ashimjk.eventhandling.sync;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class CustomEventPublisher {

  private ApplicationEventPublisher publisher;

  public void doPublishEvent(final String message) {
    log.info("Publishing custom event");
    CustomEvent customEvent = new CustomEvent(this, message);
    this.publisher.publishEvent(customEvent);
  }

}
