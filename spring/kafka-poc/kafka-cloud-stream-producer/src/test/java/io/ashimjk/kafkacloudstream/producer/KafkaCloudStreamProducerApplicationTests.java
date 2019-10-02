package io.ashimjk.kafkacloudstream.producer;

import io.restassured.RestAssured;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class KafkaCloudStreamProducerApplicationTests {

    @LocalServerPort
    private int port;

    @Test
    public void testKafkaMessage() {

        Map<String, String> value = new HashMap<>();
        value.put("value", "Hello!");

        RestAssured
                .given()
                .baseUri("http://localhost:" + port)
                .log()
                .all()
                .when()
                .get("?value=Hello World")
                .then()
                .statusCode(200);
    }

}
