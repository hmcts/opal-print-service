package uk.gov.hmcts.opal.controllers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


class SampleSmokeTest {

    private static final String DEFAULT_BASE_URL = "http://localhost:4660";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = System.getenv().getOrDefault("TEST_URL", DEFAULT_BASE_URL);
        ;
        RestAssured.useRelaxedHTTPSValidation();

    }

    @Test
    void smokeTest() {
        Response response = given()
            .contentType(ContentType.JSON)
            .when()
            .get()
            .then()
            .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertTrue(response.asString().startsWith("Welcome"));
    }

    @Test
    public void testHealthCheck() {
        RequestSpecification httpRequest = given();

        Response response = httpRequest.request(Method.GET, "/health");

        response.then().statusCode(200)
            .body("status", equalTo("UP"));
    }
}
