package io.ashimjk.entityrelationship.controller;

import io.ashimjk.entityrelationship.domain.Address;
import io.ashimjk.entityrelationship.domain.Beneficiary;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest(webEnvironment = RANDOM_PORT)
//@ExtendWith(SpringExtension.class)
class BeneficiaryControllerTest {

    //    @LocalServerPort
    private int port = 8080;

    @Test
    void givenBeneficiary_whenCreate_ThenSaveBeneficiary() {
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setAddresses(Collections.singletonList(buildAddress()));


        String response = RestAssured
                .given()
                .baseUri("http://localhost")
                .port(port)
                .contentType("application/json")
                .when()
                .body(beneficiary)
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .jsonPath()
                .prettify();


        assertThat(response)
                .isNotNull()
                .isNotEmpty();
    }


    @Test
    void givenBeneficiaryId_whenGet_ThenReturnBeneficiary() {
        String response = RestAssured
                .given()
                .baseUri("http://localhost")
                .port(port)
                .contentType("application/json")
                .when()
//                .pathParam("id", 1)
                .get("/1")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .jsonPath()
                .prettify();

        System.out.println(response);

        assertThat(response)
                .isNotNull()
                .isNotEmpty();
    }

    private Address buildAddress() {
        return Address.builder()
                .addressLine1("ktm")
                .city("ktm")
                .country("nepal")
                .build();
    }

}