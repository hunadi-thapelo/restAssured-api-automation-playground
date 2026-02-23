package org.datadriven;

import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import org.files.ReusableMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class AddBookRequest {

    @Test
    public void addBookTest() {

        LibraryAPIBaseURIConfig.setup();
        String addBookData = LibraryPayload.addBook();

        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Get ISTQB Certified with Sandy D");
        map.put("isbn", "zxhsb");
        map.put("aisle", "80001");
        map.put("author", "Sandy Doe");


        String addBookResponse = given().header("Content-Type", "application/json")
                .body(map, ObjectMapperType.JACKSON_2)
                .when().post("/Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = ReusableMethod.rawToJson(addBookResponse);
        String newBookID = js.get("ID");
        System.out.println(addBookResponse);
        System.out.println(newBookID);

    }
}
