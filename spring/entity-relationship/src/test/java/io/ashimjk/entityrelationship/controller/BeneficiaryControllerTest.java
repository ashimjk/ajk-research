package io.ashimjk.entityrelationship.controller;

import io.ashimjk.entityrelationship.domain.Address;
import io.ashimjk.entityrelationship.domain.AuthorizedSignature;
import io.ashimjk.entityrelationship.domain.Beneficiary;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
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
        beneficiary.setAuthorizedSignatories(Collections.singletonList(buildAuthorizedSignature()));


        String response = rest()
                .body(beneficiary)
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .jsonPath()
                .prettify();


        assertThat(response).isNotNull().isNotEmpty();
    }

    @Test
    void givenBeneficiary_whenEdit_ThenSaveBeneficiary() {
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setAddresses(Collections.singletonList(buildAddress()));
        beneficiary.setAuthorizedSignatories(Collections.singletonList(buildAuthorizedSignature()));

        Beneficiary response = rest()
                .body(beneficiary)
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract().as(Beneficiary.class);

        AuthorizedSignature authorizedSignature = new AuthorizedSignature();
        authorizedSignature.setNationalNumber("123");

        beneficiary = new Beneficiary();
        beneficiary.setAuthorizedSignatories(Collections.singletonList(authorizedSignature));

        String output = rest()
                .body(beneficiary)
                .put("/" + response.getId())
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .jsonPath()
                .prettify();


        assertThat(output).isNotNull().isNotEmpty();
    }

    @Test
    void givenBeneficiaryId_whenGet_ThenReturnBeneficiary() {
        String response = rest()
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

    private AuthorizedSignature buildAuthorizedSignature() {
        AuthorizedSignature authorizedSignature = new AuthorizedSignature();
        authorizedSignature.setTitle("Mr.");
        authorizedSignature.setFullName("Ashim Jung Khadka");

        return authorizedSignature;
    }


    private RequestSpecification rest() {
        return RestAssured
                .given()
                .baseUri("http://localhost")
                .port(port)
                .contentType("application/json")
                .when();
    }
}