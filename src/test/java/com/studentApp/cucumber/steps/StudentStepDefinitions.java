package com.studentApp.cucumber.steps;

import com.studentApp.cucumber.serenity.StudentSerenitySteps;
import com.studentApp.utils.TestUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class StudentStepDefinitions {

    @Steps
    StudentSerenitySteps steps;

    @Given("^User sends a GET request to the list endpoint, they must get a status code 200$")
    public void verifyStatusCode(){
        SerenityRest.rest()
                .given()
                .when()
                .get("/list")
                .then()
                .statusCode(200);


    }


    @When("^I create a new student with the info firstName (.*) lastName (.*) email(.*) programme(.*) and courses(.*)$")
    public void createStudent(String firstName,String lastName,String email,String programme,String course){
        ArrayList<String> courses = new ArrayList<>();
        courses.add(course);
        steps.createStudent(firstName,lastName,email,programme,courses)
                .assertThat()
                .statusCode(201);
    }

    @Then("^I verify that the student with (.*)is created$")
    public void verifyStudentByEmail(String email){

        HashMap<String,Object> value = steps.getStudentInfoByEmail(email);
        assertThat(value,hasValue(email));

    }
}
