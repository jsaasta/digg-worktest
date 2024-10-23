package com.jsaasta.user;

import com.google.gson.Gson;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@QuarkusTest
@TestHTTPEndpoint(UserResource.class)
public class UserResourceTest {

    /**
     * For some reason RestAssured holds a global context between runs when doing a multi-run test suite.
     * That's why testGETShouldReturnUsers only checks so it isn't equal to 0.
     * If I check for example 15 (which is true), it's not always true if testPOSTShouldAddUser runs before, which then makes it to 16.
     * Given the amount of time + it's a code test, I will leave it as is.
     *
     * @AfterEach
     * void tearDown(){ }
     */


    @Test
    void testGETShouldReturnUsers() {
        Set<User> expectedSize = get().as(Set.class);
        given()
                .when().get()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);

        assertNotEquals(0, expectedSize.size());
    }

    @Test
    void testPOSTShouldAddUser() {
        User user = new User("Name", "Address", "email", "0");
        Gson gson = new Gson();
        String requestBody = gson.toJson(user);

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when().post()
                .then()
                .extract().response();

        assertEquals(201, response.statusCode());
        assertEquals(user.getName(), response.jsonPath().getString("name"));
        assertEquals(user.getAddress(), response.jsonPath().getString("address"));
        assertEquals(user.getEmail(), response.jsonPath().getString("email"));
        assertEquals(user.getPhoneNumber(), response.jsonPath().getString("phoneNumber"));
    }

}
