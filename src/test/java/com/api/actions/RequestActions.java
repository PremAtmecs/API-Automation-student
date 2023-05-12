package com.api.actions;

import com.api.payloads.Students;
import com.api.utilities.PropertyParser;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;
public class RequestActions {

    public static Response createStudent(Object payload, String postURL){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(postURL);
        return response;
    }

    public static Response readStudent(int ID, String getURL){
        Response response = given()
                .pathParam("studentid", ID)
                .when()
                .get(getURL);
        return response;
    }

    public static Response updateStudent(int studentId, Object payload, String updateURL){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .pathParam("studentid", studentId)
                .when()
                .put(updateURL);
        return response;
    }

    public static Response deleteStudent(int studentId, String deleteURL){
        Response response = given()
                .pathParam("studentid", studentId)
                .when()
                .delete(deleteURL);
        return response;
    }

    public static Response readSpecificStudent(String resources, String studentName, String singleStudentURL){
        Response response = given()
                .pathParam("studentname", resources)
                .queryParam("name", studentName)
                .when()
                .get(singleStudentURL);
        return response;
    }
}
