package io.ashimjk.genericimpl.controller;

import io.ashimjk.genericimpl.domain.Address;
import io.ashimjk.genericimpl.domain.Beneficiary;
import io.ashimjk.genericimpl.domain.IdType;
import io.ashimjk.genericimpl.domain.authorizedsignature.AuthorizedSignature;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
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


    @Test
    void filterDate() {
        createBeneficiary(LocalDate.now());
        createBeneficiary(LocalDate.now());
        createBeneficiary(LocalDate.now());
        createBeneficiary(LocalDate.now().minusMonths(2));

        String response = rest()
                .get("/filter")
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

    void createBeneficiary(LocalDate localDate) {
        IdType idType = new IdType();
        idType.setName("NATIONAL_NUMBER");
        idType.setName("REGISTRATION_CERTIFICATE");
        idType.setName("MINISTRY_OF_INDUSTRY_CERTIFICATE");

        idType.setExpiryDate(localDate);

        Beneficiary beneficiary = new Beneficiary();
        beneficiary.addIdType(idType);
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