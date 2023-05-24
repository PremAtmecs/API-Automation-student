package com.api.pageobject;

import com.api.actions.AuthenticationActions;
import com.api.actions.RequestActions;
import com.api.constant.FilePathConstant;
import com.api.data.TestFireData;
import com.api.message.MessageInfo;
import com.api.utilities.APIUtils;
import com.api.utilities.PropertyParser;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.response.Response;
import static com.api.utilities.ReportGeneration.test;

public class AuthenticationPage {
    public PropertyParser url;
    public PropertyParser auth;
    public String token;
    public AuthenticationPage(){
        url = new PropertyParser(FilePathConstant.ROUTES);
        auth = new PropertyParser(FilePathConstant.TOKEN);
    }

    public void basicAuth(){
        String auth_url = url.getPropertyValue("postman_authentication_url");
        String username = auth.getPropertyValue("username");
        String password = auth.getPropertyValue("password");
        Response response = AuthenticationActions.basicAuthentication(username, password, "basic-auth", auth_url);
        response.then().log().all();
        test.log(LogStatus.INFO, MessageInfo.BASIC_AUTH_INFO, String.valueOf(response.getStatusLine()));
        APIUtils.validateResponse(response.statusCode(),200);
        String Auth = APIUtils.getValueByJsonPath(response.getBody().asPrettyString(), "authenticated");
        APIUtils.validateResponse(Auth, "true");
    }

    public void digestAuth(){
        String auth_url = url.getPropertyValue("postman_authentication_url");
        String username = auth.getPropertyValue("username");
        String password = auth.getPropertyValue("password");
        Response response = AuthenticationActions.digestAuthentication(username, password, "basic-auth", auth_url);
        response.then().log().all();
        test.log(LogStatus.INFO, MessageInfo.DIGEST_AUTH_INFO, String.valueOf(response.getStatusLine()));
        APIUtils.validateResponse(response.statusCode(), 200);
    }

    public void postDataToGetToken(){
        String authURL = url.getPropertyValue("testfire_url");
        Response response = RequestActions.createwithMultipleParam(TestFireData.setupData(), "api", "login", authURL);
        response.then().log().all();
        token = response.getBody().path("Authorization");
    }

    public void bearerAuthentication(){
        String authURL = url.getPropertyValue("testfire_url");
        Response response = AuthenticationActions.bearerAuth(token, "api", "login", authURL);
        response.then().log().all();
        test.log(LogStatus.INFO, MessageInfo.BEARER_AUTH_INFO, String.valueOf(response.getStatusLine()));
        APIUtils.validateResponse(response.statusCode(), 200);
    }

    public void OAuth2(){
        String authURL = url.getPropertyValue("testfire_url");
        Response response = AuthenticationActions.OAuth2(token, "api", "login", authURL);
        response.then().log().all();
        test.log(LogStatus.INFO, MessageInfo.OAUTH_INFO, String.valueOf(response.getStatusLine()));
        APIUtils.validateResponse(response.statusCode(), 200);
    }
}
