package org.basics;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.files.payload;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class BasicsDemo {
    public static void main(String[] args){

        //Test case: Validate if Add Place api is working as expected
        //Updated Test case: Add place, then update address, then get place to validate if new address is present in response

        //First set your base URL, then follow BDD
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").
                body(payload.AddPlace()).when().post("maps/api/place/add/json").then().assertThat().statusCode(200).
                log().all().body("scope", equalTo("APP")).header("server","Apache/2.4.41 (Ubuntu)").
        extract().response().asString();

        //System.out.println(response);

        //Json path to capture place_id in response
        JsonPath js = new JsonPath(response); //for parsing Json
        String placeID = js.get("place_id");

        System.out.println(placeID);




    }
}
