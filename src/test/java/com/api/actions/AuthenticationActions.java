package com.api.actions;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthenticationActions {
    public static Response basicAuthentication(String username, String password, String endpoint, String authenticationURL){
        Response response = given()
                .auth().basic(username, password)
                .pathParam("endpoint", endpoint)
                .when()
                .get(authenticationURL);
        return response;
    }

    public static Response digestAuthentication(String username, String password, String endpoint, String authenticationURL){
        Response response = given()
                .auth().digest(username, password)
                .pathParam("endpoint", endpoint)
                .when()
                .get(authenticationURL);
        return response;
    }

    public static Response bearerAuth(String token, String endpoint1, String endpoint2, String authorizationURL){
        Response response = given()
                .header("Authorization", "Bearer "+token)
                .pathParam("endpoint1", endpoint1)
                .pathParam("endpoint2", endpoint2)
                .when()
                .get(authorizationURL);
        return response;
    }

    public static Response OAuth2(String token, String endpoint1, String endpoint2, String authorizationURL){
        Response response = given()
                .auth().oauth2(token)
                .pathParam("endpoint1", endpoint1)
                .pathParam("endpoint2", endpoint2)
                .when()
                .get(authorizationURL);
        return response;
    }


}
