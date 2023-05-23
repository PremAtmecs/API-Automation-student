package com.api.utilities;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class Assertions {
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
}
