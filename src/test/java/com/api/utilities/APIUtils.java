package com.api.utilities;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.File;

public class APIUtils {
    public static void validateResponse(Object actual, Object expected){
        boolean status = false;
        try {
            Assert.assertEquals(actual, expected);
            status = true;
            System.out.println("PASS - Actual Result ::" +actual+ "==" +"Expected Result ::" +expected);
        }
        catch (AssertionError assertionError){
            System.out.println("FAIL - Actual Result ::" +actual+ "==" +"Expected Result ::" +expected);
        }

    }

    public static String getValueByJsonPath(String json, String jsonPathExpression) {
        JsonPath jsonPath = new JsonPath(json);
        return jsonPath.getString(jsonPathExpression);
    }

    public static boolean validateJsonSchema(Response response, String schemaPath) {
        try {
            response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(schemaPath)));
            return true;
        } catch (AssertionError e) {
            return false;
        }
    }
}
