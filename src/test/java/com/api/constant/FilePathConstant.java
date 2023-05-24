package com.api.constant;

import java.io.File;

public class FilePathConstant {
    public static final String RESOURCE_PATH = System.getProperty("user.dir")+ File.separator + "src" + File.separator + "test" + File.separator + "resources";
    public static final String ROUTES = RESOURCE_PATH + File.separator + "testData" + File.separator + "routes.properties";
    public static final String TOKEN = RESOURCE_PATH + File.separator + "testData" + File.separator + "tokens.properties";
    public static final String JSON_SCHEMA = RESOURCE_PATH + File.separator + "Schema" + File.separator + "student_schema.json";
}
