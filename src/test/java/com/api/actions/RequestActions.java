package com.api.actions;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
public class RequestActions {

    public static Response create(Object payload, String postURL){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(postURL);
        return response;
    }

    public static Response createwithMultipleParam(Object payload, String endpoint1, String endpoint2, String postURL){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("endpoint1", endpoint1)
                .pathParam("endpoint2", endpoint2)
                .body(payload)
                .when()
                .post(postURL);
        return response;
    }

    public static Response read(int ID, String getURL){
        Response response = given()
                .pathParam("studentid", ID)
                .when()
                .get(getURL);
        return response;
    }

    public static Response update(int studentId, Object payload, String updateURL){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .pathParam("studentid", studentId)
                .when()
                .put(updateURL);
        return response;
    }

    public static Response delete(int studentId, String deleteURL){
        Response response = given()
                .pathParam("studentid", studentId)
                .when()
                .delete(deleteURL);
        return response;
    }

    public static Response queryParam(String resources, String studentName, String singleStudentURL){
        Response response = given()
                .pathParam("studentname", resources)
                .queryParam("name", studentName)
                .when()
                .get(singleStudentURL);
        return response;
    }

}
