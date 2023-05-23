package com.api.pageobject;

import com.api.actions.RequestActions;
import com.api.data.StudentData;
import com.api.message.MessageInfo;
import com.api.payloads.Students;
import com.api.utilities.Assertions;
import com.api.utilities.PropertyParser;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.response.Response;

import static com.api.utilities.ReportGeneration.test;

public class StudentPage {
    int studentId;
    public PropertyParser propertyParser = new PropertyParser("testData.routes");

    public void createStudent(){
        String post_url = propertyParser.getFile("testData.routes").getString("post_url");
        Response response = RequestActions.create(StudentData.createStudentData(), post_url);
        response.then().log().all();
        Assertions.validateResponse(response.getStatusCode(), 201);
        test.log(LogStatus.INFO, MessageInfo.POST_INFO, String.valueOf(response.getStatusLine()));
        studentId = response.getBody().path("id");
    }

    public void readCreatedStudent(){
        String get_url = propertyParser.getFile("testData.routes").getString("get_url");
        Response response = RequestActions.read(studentId, get_url);
        response.then().log().all();
        Assertions.validateResponse(response.getStatusCode(), 200);
        test.log(LogStatus.INFO, MessageInfo.GET_INFO, String.valueOf(response.getStatusLine()));
    }

    public void updateStudent(){
        String update_url = propertyParser.getFile("testData.routes").getString("update_url");
        Response response = RequestActions.update(studentId,StudentData.updateStudentData(), update_url);
        response.then().log().all();
        test.log(LogStatus.INFO, MessageInfo.UPDATE_INFO, String.valueOf(response.getStatusLine()));
    }

    public void deleteStudent(){
        String delete_url = propertyParser.getFile("testData.routes").getString("delete_url");
        Response response = RequestActions.delete(studentId, delete_url);
        response.then().log().all();
        Assertions.validateResponse(response.getStatusCode(), 200);
        test.log(LogStatus.INFO, MessageInfo.DELETE_INFO, String.valueOf(response.getStatusLine()));
    }

    public void getSinlgeStudent(){
        String singlestudent_url = propertyParser.getFile("testData.routes").getString("singlestudent_url");
        Response response = RequestActions.queryParam("students", "prasanna", singlestudent_url);
        response.then().log().body();
        test.log(LogStatus.INFO, MessageInfo.SPECIFIC_STUDENT_INFO, String.valueOf(response.getStatusLine()));
        String name = response.getBody().path("name[1]");
        Assertions.validateResponse(name, "prasanna");
    }
}
