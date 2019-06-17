package io.ashimjk.cucumber.glue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.ashimjk.cucumber.MessageProvider;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Config.class)
public class MessageStep {

    @Autowired private MessageProvider provider;

    private ValidatableResponse response;

    @Given("message api request")
    public void messageApiRequest() {
        response = RestAssured
                .given()
                .log()
                .all()
                .baseUri("http://localhost:8080")
                .contentType(ContentType.JSON)
                .param("msg", provider.getMessage())
                .when()
                .get("/message")
                .then();
    }

    @Then("return message")
    public void returnMessage() {
        response.assertThat()
                .statusCode(200)
                .body(Matchers.equalTo("Hello World!"));
    }

}
