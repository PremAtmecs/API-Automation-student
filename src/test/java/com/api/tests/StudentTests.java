package com.api.tests;

import com.api.pageobject.StudentPage;
import com.api.utilities.ReportGeneration;
import org.testng.annotations.Test;

public class StudentTests extends ReportGeneration {
    public static StudentPage studentTest = new StudentPage();

    @Test (priority = 1)
    void testPostStudent(){
        studentTest.createStudent();
    }

    @Test (priority = 2,dependsOnMethods = "testPostStudent")
    void testGetStudent(){
        studentTest.ReadCreatedStudent();
    }

    @Test(priority = 3, dependsOnMethods = "testPostStudent")
    void testUpdateStudent(){
        studentTest.UpdateStudent();
    }

    @Test(priority = 4, dependsOnMethods = "testPostStudent")
    void testDeleteStudent(){
        studentTest.deleteStudent();
    }

    @Test (priority = 5)
    void testGetSpecificStudent(){
        studentTest.getSinlgeStudent();
    }

}
