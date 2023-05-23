package com.api.pageobject;

import com.api.actions.AuthenticationActions;
import com.api.actions.RequestActions;
import com.api.data.TestFireData;
import com.api.message.MessageInfo;
import com.api.payloads.TestFire;
import com.api.utilities.Assertions;
import com.api.utilities.PropertyParser;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import static com.api.utilities.ReportGeneration.test;

public class AuthenticationPage {
    public PropertyParser urlProperty = new PropertyParser("testData.routes");
    public PropertyParser authProperty = new PropertyParser("testData.tokens");
    public String token;

    public void basicAuth(){
        String auth_url = urlProperty.getFile("testData.routes").getString("postman_authentication_url");
        String username = authProperty.getFile("testData.tokens").getString("username");
        String password = authProperty.getFile("testData.tokens").getString("password");
        Response response = AuthenticationActions.basicAuthentication(username, password, "basic-auth", auth_url);
        response.then().log().all();
        test.log(LogStatus.INFO, MessageInfo.BASIC_AUTH_INFO, String.valueOf(response.getStatusLine()));
        Assertions.validateResponse(response.statusCode(),200);
        String Auth = Assertions.getValueByJsonPath(response.getBody().asPrettyString(), "authenticated");
        Assertions.validateResponse(Auth, "true");
    }

    public void digestAuth(){
        String auth_url = urlProperty.getFile("testData.routes").getString("postman_authentication_url");
        String username = authProperty.getFile("testData.tokens").getString("username");
        String password = authProperty.getFile("testData.tokens").getString("password");
        Response response = AuthenticationActions.digestAuthentication(username, password, "basic-auth", auth_url);
        response.then().log().all();
        test.log(LogStatus.INFO, MessageInfo.DIGEST_AUTH_INFO, String.valueOf(response.getStatusLine()));
        Assertions.validateResponse(response.statusCode(), 200);
    }

    public void postDataToGetToken(){
        String authURL = urlProperty.getFile("testData.routes").getString("testfire_url");
        Response response = RequestActions.createwithMultipleParam(TestFireData.setupData(), "api", "login", authURL);
        response.then().log().all();
        token = response.getBody().path("Authorization");
    }

    public void bearerAuthentication(){
        String authURL = urlProperty.getFile("testData.routes").getString("testfire_url");
        Response response = AuthenticationActions.bearerAuth(token, "api", "login", authURL);
        response.then().log().all();
        test.log(LogStatus.INFO, MessageInfo.BEARER_AUTH_INFO, String.valueOf(response.getStatusLine()));
        Assertions.validateResponse(response.statusCode(), 200);
    }

    public void OAuth2(){
        String authURL = urlProperty.getFile("testData.routes").getString("testfire_url");
        Response response = AuthenticationActions.OAuth2(token, "api", "login", authURL);
        response.then().log().all();
        test.log(LogStatus.INFO, MessageInfo.OAUTH_INFO, String.valueOf(response.getStatusLine()));
        Assertions.validateResponse(response.statusCode(), 200);
    }
}
