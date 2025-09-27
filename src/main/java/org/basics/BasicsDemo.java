package org.basics;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.files.ReusableMethod;
import org.files.payload;
import org.testng.Assert;

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
                log().all().body("scope", equalTo("APP")).header("server","Apache/2.4.52 (Ubuntu)").
        extract().response().asString();


        //Json path to capture place_id in response
        JsonPath js = new JsonPath(response); //for parsing Json
        String placeID = js.get("place_id");


        //Update address
        String newAddress = "20 Boston Hill, Johannesburg";

        given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").
                body("{\n" +
                        "\"place_id\":\""+placeID+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}").when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200).
                body("msg",equalTo("Address successfully updated"));

        //Get place
        String getPlaceResponse = given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeID)
                .when().get("maps/api/place/get/json").then().assertThat().log().all().statusCode(200).extract().response().asString();


        //JsonPath jsonP = new JsonPath(getPlaceResponse); //for parsing Json
        JsonPath jsonP = ReusableMethod.rawToJson(getPlaceResponse);
        String actualNewAddress = jsonP.get("address");

        Assert.assertEquals(actualNewAddress,newAddress); //testNG assert to validate





    }
}
