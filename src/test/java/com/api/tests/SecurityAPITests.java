package com.api.tests;

import com.api.pageobject.AuthenticationPage;
import com.api.utilities.ReportGeneration;
import org.testng.annotations.Test;

public class SecurityAPITests extends ReportGeneration{
    public static AuthenticationPage authentication = new AuthenticationPage();

    @Test (priority = 1)
    void testAuthentication(){
        authentication.basicAuth();
    }

    @Test (priority = 2)
    void testDigestAuthentication(){
        authentication.digestAuth();
    }

    @Test (priority = 3)
    void testGetToken(){
        authentication.postDataToGetToken();
    }

    @Test (priority = 4, dependsOnMethods = "testGetToken")
    void testBearerAuthorization(){
        authentication.bearerAuthentication();
    }

    @Test (priority = 5, dependsOnMethods = "testGetToken")
    void testOpenAuthorization(){
        authentication.OAuth2();
    }

}
