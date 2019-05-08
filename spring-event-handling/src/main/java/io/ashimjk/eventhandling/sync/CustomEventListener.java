package io.ashimjk.eventhandling.sync;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomEventListener implements ApplicationListener<CustomEvent> {

  @Override
  public void onApplicationEvent(CustomEvent event) {
    log.info("Received custom event : " + event.getMessage());
  }
}
