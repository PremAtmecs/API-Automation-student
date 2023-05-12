package com.api.pageobject;

import com.api.actions.RequestActions;
import com.api.data.StudentData;
import com.api.utilities.Assertions;
import com.api.utilities.PropertyParser;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.response.Response;
import static com.api.utilities.ReportGeneration.test;

public class StudentPage {
    int studentId;
    public static PropertyParser propertyParser = new PropertyParser("url.routes");

    public void createStudent(){
        String post_url = propertyParser.getURL("url.routes").getString("post_url");
        Response response = RequestActions.createStudent(StudentData.createStudentData(), post_url);
        response.then().log().all();
        Assertions.validateResponse(response.getStatusCode(), 201);
        test.log(LogStatus.INFO, "Post student details", String.valueOf(response.getStatusLine()));
        studentId = response.getBody().path("id");
    }

    public void ReadCreatedStudent(){
        String get_url = propertyParser.getURL("url.routes").getString("get_url");
        Response response = RequestActions.readStudent(studentId, get_url);
        response.then().log().all();
        Assertions.validateResponse(response.getStatusCode(), 200);
        test.log(LogStatus.INFO, "Get student details", String.valueOf(response.getStatusLine()));
    }

    public void UpdateStudent(){
        String update_url = propertyParser.getURL("url.routes").getString("update_url");
        Response response = RequestActions.updateStudent(studentId,StudentData.updateStudentData(), update_url);
        response.then().log().all();
        test.log(LogStatus.INFO, "Update student details", String.valueOf(response.getStatusLine()));
    }

    public void deleteStudent(){
        String delete_url = propertyParser.getURL("url.routes").getString("delete_url");
        Response response = RequestActions.deleteStudent(studentId, delete_url);
        response.then().log().all();
        Assertions.validateResponse(response.getStatusCode(), 200);
        test.log(LogStatus.INFO, "Delete student details", String.valueOf(response.getStatusLine()));
    }

    public void getSinlgeStudent(){
        String singlestudent_url = propertyParser.getURL("url.routes").getString("singlestudent_url");
        Response response = RequestActions.readSpecificStudent("students", "prasanna", singlestudent_url);
        response.then().log().body();
        test.log(LogStatus.INFO, "Get specific student details", String.valueOf(response.getStatusLine()));
    }
}
