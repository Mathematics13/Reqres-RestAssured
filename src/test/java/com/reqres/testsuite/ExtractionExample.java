package com.reqres.testsuite;

import com.reqres.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import java.util.List;
import static io.restassured.RestAssured.given;

public class ExtractionExample extends TestBase {
    static ValidatableResponse response;                                          //ValidatableResponse is an interface

    @Test
    public void userExtraction() {                                              //Extracting results  from List Users
        response =
                given()

                        .when()
                        .get("/?page=2")

                        .then().statusCode(200);

        //1)Print the size of the data
        List<Integer> length = response.extract().path("data.length");
        System.out.println("Size of the data : " +length.size() );

        //2)Extract the list of IDS of all users
        List<Integer> listOfIDS  = response.extract().path("data.id");
        System.out.println( listOfIDS);
        System.out.println(listOfIDS .get(0));

        //3)Extract the value of per_page
        int value = response.extract().path("per_page");
        System.out.println("Value of per_page:" +value);


        //4)Get the email for last name "Lawson"
       List<String> email = response.extract().path("data.findAll{it.last_name == 'Lawson'}.email");
        System.out.println(email);

        //5)Get the id of users whose first-name starts = 'Geo'
        List<Integer> id = response.extract().path("data.findAll{it.first_name.startsWith('Geo')}.id");
        System.out.println(id);
    }
}
