package com.api.pageobject;

import com.api.actions.RequestActions;
import com.api.constant.FilePathConstant;
import com.api.data.StudentData;
import com.api.message.MessageInfo;
import com.api.utilities.APIUtils;
import com.api.utilities.PropertyParser;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.response.Response;

import static com.api.utilities.ReportGeneration.test;

public class StudentPage {
    int studentId;
    public PropertyParser url;
    public StudentPage(){
        url = new PropertyParser(FilePathConstant.ROUTES);
    }

    public void createStudent(){
        String post_url = url.getPropertyValue("post_url");
        Response response = RequestActions.create(StudentData.createStudentData(), post_url);
        response.then().log().all();
        APIUtils.validateResponse(response.getStatusCode(), 201);
        APIUtils.validateJsonSchema(response, FilePathConstant.JSON_SCHEMA);
        test.log(LogStatus.INFO, MessageInfo.POST_INFO, String.valueOf(response.getStatusLine()));
        studentId = response.getBody().path("id");
    }

    public void readCreatedStudent(){
        String get_url = url.getPropertyValue("get_url");
        Response response = RequestActions.read(studentId, get_url);
        response.then().log().all();
        APIUtils.validateResponse(response.getStatusCode(), 200);
        test.log(LogStatus.INFO, MessageInfo.GET_INFO, String.valueOf(response.getStatusLine()));
    }

    public void updateStudent(){
        String update_url = url.getPropertyValue("update_url");
        Response response = RequestActions.update(studentId,StudentData.updateStudentData(), update_url);
        response.then().log().all();
        test.log(LogStatus.INFO, MessageInfo.UPDATE_INFO, String.valueOf(response.getStatusLine()));
    }

    public void deleteStudent(){
        String delete_url = url.getPropertyValue("delete_url");
        Response response = RequestActions.delete(studentId, delete_url);
        response.then().log().all();
        APIUtils.validateResponse(response.getStatusCode(), 200);
        test.log(LogStatus.INFO, MessageInfo.DELETE_INFO, String.valueOf(response.getStatusLine()));
    }

    public void getSinlgeStudent(){
        String singlestudent_url = url.getPropertyValue("singlestudent_url");
        Response response = RequestActions.queryParam("students", "prasanna", singlestudent_url);
        response.then().log().body();
        test.log(LogStatus.INFO, MessageInfo.SPECIFIC_STUDENT_INFO, String.valueOf(response.getStatusLine()));
        String name = response.getBody().path("name[1]");
        APIUtils.validateResponse(name, "prasanna");
    }
}
