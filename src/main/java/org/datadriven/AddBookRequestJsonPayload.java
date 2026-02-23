package org.datadriven;

import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import org.files.ReusableMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class AddBookRequestJsonPayload {

    @Test
    public void addBookTest() {

        LibraryAPIBaseURIConfig.setup();
        String addBookData = LibraryPayload.addBook();

        String addBookResponse = given().header("Content-Type", "application/json")
                .body(addBookData)
                .when().post("/Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = ReusableMethod.rawToJson(addBookResponse);
        String newBookID = js.get("ID");
        System.out.println(addBookResponse);
        System.out.println(newBookID);

        String getBookAdded = given().queryParam("AuthorName", "Sandy P Doe")
                .when().log().all().get("/Library/GetBook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

//        JsonPath js1 = ReusableMethod.rawToJson(getBookAdded);
//        String newqBookISBN = js1.get("isbn");
//        System.out.println(getBookAdded);
//        System.out.println("ISBN: " +newqBookISBN);

        System.out.println("This is my get response" + getBookAdded);

    }
}
