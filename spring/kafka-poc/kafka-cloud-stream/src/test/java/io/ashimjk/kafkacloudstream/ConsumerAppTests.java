package io.ashimjk.kafkacloudstream;

import io.restassured.RestAssured;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ConsumerAppTests {

    @LocalServerPort
    private int port;

    @Test
    public void sendKafkaMessageToOutput() {
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
