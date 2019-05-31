package io.ashimjk;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ConditionalOnProperty(
        name = "io.ashimjk.scheduling.enabled",
        havingValue = "true",
        matchIfMissing = true)
public class SchedulingConfiguration {
}
