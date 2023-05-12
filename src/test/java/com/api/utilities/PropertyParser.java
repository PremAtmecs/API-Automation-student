package com.api.utilities;

import java.util.ResourceBundle;

public class PropertyParser {

    public PropertyParser(String FilePath){
        getURL(FilePath);
    }

    public ResourceBundle getURL(String FilePath){
        ResourceBundle routes = ResourceBundle.getBundle(FilePath);
        return routes;
    }

}
