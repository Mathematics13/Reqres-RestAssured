package com.reqres.testsuite;

import com.reqres.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class AssertionExample extends TestBase
{
    static ValidatableResponse response;

    @Test
    public void userAssert()
    {
        response =
                given()

                        .when()
                        .get("/?page=2")

                        .then().statusCode(200);

        //1) Verify value of page is 2
        response.body("page" , equalTo(2));

        //2)Verify first name of id 12 is 'Rachel'
        response.body("data[5].first_name" ,equalTo("Rachel"));

        //3)Verify value of total is 12
        response.body("total" ,equalTo(12));

        //4)Verify avatar of Tobias is "https://reqres.in/img/faces/9-image.jpg"
        response.body("data[2].avatar" ,equalTo("https://reqres.in/img/faces/9-image.jpg"));

        //5)Verify the key 'last name' under 4th element of data
        response.body("data[3]" ,hasKey("last_name"));

        //6)Verify the url of support
        response.body("support.url" , equalTo("https://reqres.in/#support-heading"));
    }

}
