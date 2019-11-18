package io.ashimjk.keycloak;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiBasedTest {

    private static String TOKEN = "";

    @BeforeAll
    static void initToken() {
        TOKEN = token();
    }

    private static String token() {
        Map<String, String> data = new HashMap<>();
        data.put("client_id", "api-based");
        data.put("client_secret", "d84c44b6-52de-4c3a-a9d9-0fc50e2a5397");
        data.put("username", "ashim");
        data.put("password", "ashim");
        data.put("grant_type", "password");

        return RestAssured
                .given()
                .baseUri("http://localhost")
                .port(8180)
                .when()
                .formParams(data)
                .post("/auth/realms/scope-based/protocol/openid-connect/token")
                .then()
                .extract()
                .jsonPath()
                .get("access_token")
                .toString();

    }

    @Test
    @DisplayName("Api should work properly")
    void apiTest() {
        String response = restAssured()
                .get()
                .then()
                .extract()
                .jsonPath()
                .prettify();

        System.out.println("------------------");
        System.out.println(response);
        System.out.println("------------------");
    }

    private RequestSpecification restAssured() {
        return RestAssured
                .given()
                .baseUri("http://localhost")
                .port(8082)
                .when()
                .auth()
                .oauth2(TOKEN);
    }

    @Test
    @DisplayName("When get /books api called should return list of books")
    void testGetBookApi() {
        String response = restAssured()
                .get("/books")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("[0].id");

        assertEquals("B01", response);
    }

    @Test
    @DisplayName("When post /books api called should return list of books")
    void testPostBookApi() {
        String response = restAssured()
                .post("/books")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("[0].id");

        assertEquals("B01", response);
    }

    @Test
    @DisplayName("When /manager api called should return list of books")
    void testManagerApi() {
        String response = restAssured()
                .get("/manager")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .get("[0].id");

        assertEquals("B01", response);
    }

}
