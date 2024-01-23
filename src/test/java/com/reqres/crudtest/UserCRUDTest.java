package com.reqres.crudtest;

import com.reqres.model.UserPojo;
import com.reqres.testbase.TestBase;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class UserCRUDTest  extends TestBase
{
    @Test(priority=-2)
    public void getListUsers()
    {
        given()
                .header("Content-Type" , "application/json; charset=utf-8")
                .contentType(ContentType.JSON)

                .when()
                .get("/?page=2")

                .then()
                .statusCode(200)
                .log().all();
    }
    @Test(priority=-1)
    public void postCreateUser()
    {
        UserPojo userPojo = new UserPojo();
        userPojo.setName("Kamal Haasan");
        userPojo.setJob("Actor");

        given()
                .header("Content-Type" , "application/json; charset=utf-8")
                .contentType(ContentType.JSON)

                .when()
                .body(userPojo)
                .post()

                .then()
                .statusCode(201)
                .log().all();
    }
    @Test(priority=0)
    public void putUpdateUser()                                    //https://reqres.in/api/users/2
    {
        UserPojo userPojo = new UserPojo();
        userPojo.setName("RajniKanth");
        userPojo.setJob("SuperStar");

        given()
                .header("Content-Type" , "application/json; charset=utf-8")
                .contentType(ContentType.JSON)

                .when()
                .body(userPojo)
                .put("/id")

                .then()
                .statusCode(200)
                .log().all();
    }
                                                                //https://reqres.in/api/users/2
    @Test(priority=1)
    public void deleteUser()
    {

        given()

                .when()
                .delete("/id")

                .then()
                .statusCode(204)
                .log().all();
    }

}
