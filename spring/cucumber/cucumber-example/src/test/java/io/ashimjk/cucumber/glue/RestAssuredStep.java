package io.ashimjk.cucumber.glue;

import com.github.tomakehurst.wiremock.WireMockServer;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class RestAssuredStep {

    private static final String CREATE_PATH = "/create";
    private static final String APPLICATION_JSON = "application/json; charset=UTF-8";

    private final WireMockServer wireMockServer = new WireMockServer(8080);
    private ValidatableResponse response;

    @When("users upload data on a project")
    public void usersUploadDataOnAProject(String payload) throws IOException {
        wireMockServer.start();

        configureFor("localhost", wireMockServer.port());
        stubFor(post(urlEqualTo(CREATE_PATH))
                .withHeader("Content-Type", equalTo(APPLICATION_JSON))
                .withRequestBody(containing("testing-framework"))
                .willReturn(aResponse().withStatus(200)));

        response = RestAssured
                .given()
                .log()
                .all()
                .body(payload)
                .contentType(ContentType.JSON)
                .when()
                .post("http://localhost:8080/create")
                .then();

        wireMockServer.stop();
    }

    @Then("the server should handle it and return a success status")
    public void theServerShouldHandleItAndReturnASuccessStatus() {
        response.assertThat()
                .statusCode(200);
    }

    @When("users want to get information on the Cucumber project")
    public void usersWantToGetInformationOnTheCucumberProject() {
        wireMockServer.start();

        configureFor("localhost", wireMockServer.port());
        stubFor(get(urlEqualTo("/projects/cucumber"))
                .withHeader("Accept", equalTo(APPLICATION_JSON))
                .willReturn(aResponse().withBody(jsonString())));

        RestAssured.defaultParser = Parser.JSON;

        response = RestAssured.given()
                .log()
                .all()
                .accept(APPLICATION_JSON)
                .when()
                .get("http://localhost:8080/projects/cucumber")
                .then()
                .assertThat()
                .statusCode(200);

        wireMockServer.stop();
    }

    private String jsonString() {
        return "{\n" +
                "      \"testing-framework\": \"cucumber\",\n" +
                "      \"supported-language\":\n" +
                "      [\n" +
                "        \"Ruby\",\n" +
                "        \"Java\",\n" +
                "        \"Javascript\",\n" +
                "        \"PHP\",\n" +
                "        \"Python\",\n" +
                "        \"C++\"\n" +
                "      ],\n" +
                "      \"website\": \"cucumber.io\"\n" +
                "    }";
    }

    @Then("the requested data is returned")
    public void theRequestedDataIsReturned() {
        response.assertThat()
                .body("testing-framework", Matchers.equalTo("cucumber"))
                .body("website", Matchers.equalTo("cucumber.io"));
    }

}
