package io.ashimjk.testing;

import io.ashimjk.SchedulingConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = "io.ashimjk.scheduling.enabled=false")
class SchedulingTest {

    @Autowired(required = false)
    private SchedulingConfiguration schedulingConfiguration;

    @Test
    void test() {
        assertThat(schedulingConfiguration).isNull();
    }

}
