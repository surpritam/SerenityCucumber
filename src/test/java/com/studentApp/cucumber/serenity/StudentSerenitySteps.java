package com.studentApp.cucumber.serenity;

import com.studentApp.model.Student;
import com.studentApp.utils.ReusableSpecifications;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentSerenitySteps {

    @Step("To create a student with First name:{0}, Last Name:{1},Email:{2},Programme:{3},Courses:{4}")
    public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme, ArrayList<String> courses){

        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setProgramme(programme);
        student.setCourses(courses);

        return SerenityRest.rest().given()
                .spec(ReusableSpecifications.getGenericRequestSpec())
                .when()
                .body(student)
                .post()
                .then();
    }

    @Step("To get student info by email :{0}")
    public HashMap<String,Object> getStudentInfoByEmail(String email){
        String p1 = "findAll{it.email=='";
        String p2 = "'}.get(0)";
        return SerenityRest.rest().given()
                .when()
                .get("/list")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .path(p1 + email + p2);
    }
}
