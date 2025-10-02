package org.basics;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.files.ReusableMethod;
import org.files.payload;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson {

    @Test
    public void addBook() {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().header("Content-Type", "application/json").
                body(payload.addBook("ISB1234b", "008")).when().post("/Library/Addbook.php").then().log().all()
                .assertThat().statusCode(200).extract().response().asString();

        JsonPath js = ReusableMethod.rawToJson(response);
        //Test case is to get ID from response
        String id = js.get("ID");
        System.out.println("ID: " + id);
    }
}
