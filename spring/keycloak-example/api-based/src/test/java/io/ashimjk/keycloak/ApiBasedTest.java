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
        data.put("username", "ashim");
        data.put("password", "ashim");
        data.put("grant_type", "password");

        return RestAssured
                .given()
                .baseUri("http://localhost")
                .port(8180)
                .when()
                .formParams(data)
                .post("/auth/realms/demo/protocol/openid-connect/token")
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
    @DisplayName("When /books api called should return list of books")
    void testBookApi() {
        String response = restAssured()
                .get("/books")
                .then()
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
                .extract()
                .jsonPath()
                .get("[0].id");

        assertEquals("B01", response);
    }

}
