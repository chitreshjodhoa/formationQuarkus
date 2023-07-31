package com.mongodb.repo;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;

public class MongoResourceJUnitTest {

    @Test
    public void testGetMyDataWithTransportParam() {
        given()
                .when().get("/my-resources/get-list?transport=Taxi")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("address", hasItems("Curepipe", "Dagotiere"))
                .body("dept", hasItems("Digital", "Digital"))
                .body("name", hasItems("Tonton", "Nikhil"))
                .body("type", hasItems("Taxi", "Taxi"));
    }

    @Test
    public void testGetMyDataWithoutTransportParam() {
        given()
                .when().get("/my-resources/get-list")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("address", hasItems("Curepipe", "Dagotiere", "Amaury"))
                .body("dept", hasItems("Digital", "Digital", "Digital"))
                .body("name", hasItems("Tonton", "Nikhil", "Chitresh"))
                .body("type", hasItems("Taxi", "Taxi", "Perso"));
    }

    @Test
    public void testGetMyDataWithInvalidTransportParam() {
        given()
                .when().get("/my-resources/get-list?transport=test")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("address", hasItems("Curepipe", "Dagotiere", "Amaury"))
                .body("dept", hasItems("Digital", "Digital", "Digital"))
                .body("name", hasItems("Tonton", "Nikhil", "Chitresh"))
                .body("type", hasItems("Taxi", "Taxi", "Perso"));
    }
}
