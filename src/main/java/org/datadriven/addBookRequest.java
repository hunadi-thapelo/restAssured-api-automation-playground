package org.datadriven;

import io.restassured.path.json.JsonPath;
import org.files.ReusableMethod;

import static io.restassured.RestAssured.given;

public class addBookRequest {

    public static void main(String[] args) {

        LibraryAPIBaseURIConfig.setup();
        String addBookData = librarypayload.addBook();

        String addBookResponse = given().header("Content-Type", "application/json")
                .body(addBookData)
                .when().post("/Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = ReusableMethod.rawToJson(addBookResponse);
        String newBookID = js.get("ID");
        System.out.println(newBookID);


    }
}
