package com.api.utilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;

public class ReportGeneration {
    public static ExtentReports extent;
    public static ExtentTest test;
    @BeforeSuite
    public void beforeSuite() {
        String USER_DIRECTORY = System.getProperty("user.dir");
        String extentPath = USER_DIRECTORY + File.separator + "reports" + File.separator + "StudentAPI.html";
        extent = new ExtentReports(extentPath);
    }

    @BeforeMethod
    public void beforeMethod(Method method){
        test = extent.startTest((this.getClass().getSimpleName()+"::"+method.getName()),method.getName());
    }

    @AfterMethod
    public void afterMethod() {
        extent.endTest(test);
    }

    @AfterSuite
    public void afterSuite(){
        extent.flush();
        extent.close();
    }
}
