package com.api.utilities;

import org.testng.Assert;

public class Assertions {
    public static void validateResponse(Object actual, Object expected){
        boolean status = false;
        try {
            Assert.assertEquals(actual, expected);
            status = true;
            System.out.println("PASS - Actual status code ::" +actual+ "==" +"Expected status code ::" +expected);
        }
        catch (AssertionError assertionError){
            System.out.println("FAIL - Actual status code ::" +actual+ "==" +"Expected status code ::" +expected);
        }

    }
}
