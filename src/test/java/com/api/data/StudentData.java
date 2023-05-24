package com.api.data;

import com.api.pojoclass.Students;
import com.github.javafaker.Faker;

public class StudentData {
    public static Faker faker = new Faker();
    public static Students studentsPayload = new Students();
    public static Students createStudentData(){

        studentsPayload.setId(faker.idNumber().hashCode());
        studentsPayload.setName(faker.name().fullName());
        studentsPayload.setEmail(faker.internet().emailAddress());
        studentsPayload.setYear(faker.date().hashCode());
        studentsPayload.setLanguage(faker.programmingLanguage().name());
        studentsPayload.setPurpose(faker.job().position());
        studentsPayload.setProgramme(faker.job().title());
        studentsPayload.setComments(faker.code().toString());
        studentsPayload.setStatus(faker.bool().bool());
        return studentsPayload;
    }

    public static Students updateStudentData(){
        studentsPayload.setName(faker.name().fullName());
        studentsPayload.setEmail(faker.internet().emailAddress());
        studentsPayload.setYear(faker.date().hashCode());
        studentsPayload.setLanguage(faker.programmingLanguage().name());
        studentsPayload.setPurpose(faker.job().position());
        studentsPayload.setProgramme(faker.job().title());
        studentsPayload.setComments(faker.code().toString());
        studentsPayload.setStatus(faker.bool().bool());
        return studentsPayload;
    }
}
