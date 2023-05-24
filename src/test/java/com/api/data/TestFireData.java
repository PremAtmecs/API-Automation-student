package com.api.data;

import com.api.pojoclass.TestFire;

public class TestFireData {
    public static TestFire testFire = new TestFire();
    public static TestFire setupData(){
        testFire.setUsername("jsmith");
        testFire.setPassword("demo1234");
        return testFire;
    }
}
