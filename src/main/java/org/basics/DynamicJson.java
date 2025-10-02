package org.basics;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.files.ReusableMethod;
import org.files.payload;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson {

    @Test (dataProvider = "booksData")
    public void addBook(String isbn, String aisle) {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().header("Content-Type", "application/json").
                body(payload.addBook(isbn, aisle)).when().post("/Library/Addbook.php").then().log().all()
                .assertThat().statusCode(200).extract().response().asString();

        JsonPath js = ReusableMethod.rawToJson(response);
        //Test case is to get ID from response
        String id = js.get("ID");
        System.out.println("ID: " + id);
    }

    @DataProvider (name = "booksData")
    public Object[][] getTestData (){

        return new Object[][] {{"ISB1s234c", "009"},{"ISB0s004c", "078"}, {"ISB5d6bs", "892"}};
    }
}
