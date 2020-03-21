package io.ashimjk.spring.cloud.local.config;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class LocalConfigApplicationTests {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void whenRequestedForApp1DefaultConfiguration_shouldReturnGreetingValue() {
        RestAssured.given()
                .when()
                .log()
                .all()
                .get("app1/default")
                .then()
                .statusCode(200)
                .log()
                .body()
                .assertThat()
                .body("propertySources[0].source.greeting", Matchers.equalTo("hello"));
    }

    @Test
    void whenRequestedForApp2DefaultConfiguration_shouldReturnGreetingValue() {
        RestAssured.given()
                .when()
                .log()
                .all()
                .get("app2/default")
                .then()
                .statusCode(200)
                .log()
                .body()
                .assertThat()
                .body("propertySources[0].source.greeting", Matchers.equalTo("hey there"));
    }

    @Test
    void whenRequestedForApp3DefaultConfiguration_shouldReturnGreetingValue() {
        RestAssured.given()
                .when()
                .log()
                .all()
                .get("app3/default")
                .then()
                .statusCode(200)
                .log()
                .body()
                .assertThat()
                .body("propertySources[0].source.greeting", Matchers.equalTo("howdy"));
    }

}
