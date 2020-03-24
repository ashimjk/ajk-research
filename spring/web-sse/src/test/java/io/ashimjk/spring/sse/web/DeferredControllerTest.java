package io.ashimjk.spring.sse.web;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class DeferredControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void testDeferredResult() {
        RestAssured.given()
                .log()
                .all()
                .get("/async-deferred-result")
                .then()
                .log()
                .all()
                .statusCode(200);
    }

    @Test
    void testTimeoutHandling() {
        RestAssured.given()
                .log()
                .all()
                .get("/timeout")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(408)
                .body(equalTo("Request timout occurred."));
    }

    @Test
    void testErrorHandling() {
        RestAssured.given()
                .log()
                .all()
                .get("/api-error")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(500)
                .body(equalTo("Api Server Error"));
    }

}
