package com.api.utilities;

import java.util.ResourceBundle;

public class PropertyParser {

    public PropertyParser(String FilePath){
        getFile(FilePath);
    }

    public ResourceBundle getFile(String FilePath){
        ResourceBundle routes = ResourceBundle.getBundle(FilePath);
        return routes;
    }

}
