package io.ashimjk.cucumber.glue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@SpringBootTest(webEnvironment = DEFINED_PORT, properties = "server.port=8080")
public class HelloWorldStep {

    private ValidatableResponse response;

    @Given("hello world api request")
    public void helloWorldApiRequest() {
        response = RestAssured
                .given()
                .log()
                .all()
                .baseUri("http://localhost:8080")
                .contentType(ContentType.JSON)
                .when()
                .get("/hello-world")
                .then();
    }

    @Then("return hello world")
    public void returnHelloWorld() {
        response.assertThat()
                .statusCode(200)
                .body(Matchers.equalTo("Hello World!"));
    }

}
