package io.ashimjk.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class Scheduler {

    /**
     * runs every 1 second
     */
    @Scheduled(fixedRate = 1000)
    public void fixedRate_1000() {
        log.info("fixedRate = 1000 : {}", LocalDateTime.now());
    }

    /**
     * runs every 1 second
     */
    @Scheduled(fixedRateString = "1000")
    public void fixedRateString_1000() {
        log.info("fixedRateString = 1000 : {}", LocalDateTime.now());
    }

    /**
     * Runs 1 second after the previous invocation finished
     */
    @Scheduled(fixedDelay = 1000)
    public void fixedDeplay_1000() {
        log.info("fixedDelay = 1000 : {}", LocalDateTime.now());
    }

    /**
     * Runs every second but waits 5 seconds before it executes for the first time
     */
    @Scheduled(fixedRate = 1000, initialDelay = 5000)
    public void fixedRateWithInitialDelay_1000() {
        log.info("fixedRate = 1000, initialDelay = 5000 : {}", LocalDateTime.now());
    }

}