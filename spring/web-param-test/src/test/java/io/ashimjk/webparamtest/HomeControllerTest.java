package io.ashimjk.webparamtest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeControllerTest {

    @LocalServerPort
    private int port;

    @Before

    @Test
    public void testApi() {
        apiTest()
            .get()
            .then()
            .statusCode(OK.value())
            .body(Matchers.equalTo("Ok"));
    }

    @Test
    public void testQueryParamsWithMap() {
        Map<String, Object> filterParams = new HashMap<>();
        filterParams.put("name", "ashim");
        filterParams.put("address", "khadka bhadrakali");

        apiTest()
            .queryParams(filterParams)
            .get("/queryParamsWithMap")
            .then()
            .statusCode(OK.value())
            .body(Matchers.equalTo(toString(filterParams)));
    }

    @Test
    public void testQueryParamsWithObject() {
        Person person = new Person("ashim", "khadka bhadrakali");

        apiTest()
            .queryParams(toMap(person))
            .get("/queryParamsWithObject")
            .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void testQueryParamsWithoutAnnotation() {
        Person person = new Person("ashim", "khadka bhadrakali");

        apiTest()
            .queryParams(toMap(person))
            .get("/queryParamsWithoutAnnotation")
            .then()
            .statusCode(OK.value())
            .body(Matchers.equalTo(toString(person)));
    }

    @Test
    public void testQueryParamsWithoutAnnotationForMap() {
        Map<String, Object> filterParams = new HashMap<>();
        filterParams.put("name", "ashim");
        filterParams.put("address", "khadka bhadrakali");

        apiTest()
            .queryParams(filterParams)
            .get("/queryParamsWithoutAnnotationForMap")
            .then()
            .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @Test
    public void testRequestBodyWithMap() {
        Map<String, Object> filterParams = new HashMap<>();
        filterParams.put("name", "ashim");
        filterParams.put("address", "khadka bhadrakali");

        apiTest()
            .body(toString(filterParams))
            .get("/requestBodyWithMap")
            .then()
            .statusCode(OK.value())
            .body(Matchers.equalTo(toString(filterParams)));
    }

    @Test
    public void testRequestBodyWithObject() {
        Person person = new Person("ashim", "khadka bhadrakali");

        apiTest()
            .body(toString(person))
            .get("/requestBodyWithObject")
            .then()
            .statusCode(OK.value())
            .body(Matchers.equalTo(toString(person)));

    }

    @SneakyThrows
    private String toString(Object params) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(params);
    }

    private RequestSpecification apiTest() {
        return RestAssured
            .given()
            .baseUri("http://localhost:" + port)
            .log()
            .all()
            .contentType("application/json")
            .when();
    }

    @SneakyThrows
    private Map<String, Object> toMap(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(object, new TypeReference<Map<String, Object>>() {});
    }
}