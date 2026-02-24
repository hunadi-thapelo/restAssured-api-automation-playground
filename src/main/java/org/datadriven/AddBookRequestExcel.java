package org.datadriven;

import io.restassured.path.json.JsonPath;
import org.datadrivendemo.ExcelDataDriven;
import org.files.ReusableMethod;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class AddBookRequestExcel {

    @Test
    public void addBookTest(ITestContext context) throws IOException {

        LibraryAPIBaseURIConfig.setup();

        ExcelDataDriven addBook = new ExcelDataDriven();
        ArrayList<String> testData = addBook.getData("AddBook", "restassured");

        HashMap<String, Object> map = new HashMap<>();
        map.put("name", testData.get(1));
        map.put("isbn", testData.get(2));
        map.put("aisle", testData.get(3));
        map.put("author", testData.get(4));


        String addBookResponse = given().header("Content-Type", "application/json")
                .body(map)
                .when().post("/Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = ReusableMethod.rawToJson(addBookResponse);
        String newBookID = js.get("ID");
        System.out.println(addBookResponse);
        //System.out.println("ID: " +newBookID);
        System.out.println("[" + newBookID + "]");
        System.out.println(newBookID.length());
        context.setAttribute("ID",newBookID);
    }

    @Test(dependsOnMethods = "addBookTest")
    public void getAddBook(ITestContext context){
        //GET
        //LibraryAPIBaseURIConfig.setup();
        String newBookID = (String) context.getAttribute("ID");

        String getBookAdded = given().log().all()
                .queryParam("ID", newBookID)
                .when().get("/Library/GetBook.php")
                .then().assertThat().statusCode(200).log().all().extract().response().asString();

        JsonPath js1 = ReusableMethod.rawToJson(getBookAdded);
        String newBookISBN = js1.getString("isbn");
        System.out.println(getBookAdded);
        System.out.println("ISBN: " + newBookISBN);

        System.out.println("This is my get response" + getBookAdded);
    }
}
