package io.ashimjk.kafka.poc;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaStreamApplicationTests {

    @Test
    public void contextLoads() {
        LOG.info("Got message: ");
    }

}
