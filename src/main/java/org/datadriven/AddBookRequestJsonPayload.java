package org.datadriven;

import io.restassured.path.json.JsonPath;
import org.files.ReusableMethod;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import io.qameta.allure.*;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@Epic("Library Service")
public class AddBookRequestJsonPayload {

    @Test
    @Story("Add a new book")
    @Description("Verify that user can add a new book to the library")
    public void addBookTest(ITestContext context) {

        LibraryAPIBaseURIConfig.setup();

        String addBookData = LibraryPayload.addBook();

        String addBookResponse = given().header("Content-Type", "application/json")
                .body(addBookData)
                .when().post("/Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response().asString();

        JsonPath js = ReusableMethod.rawToJson(addBookResponse);
        String newBookID = js.get("ID");
        System.out.println(newBookID);
        context.setAttribute("ID", newBookID);
    }

    @Test(dependsOnMethods = "addBookTest")
    @Story("Get book by ID")
    @Description("Verify that user can search a book by ID")
    public void getBookByIDTest(ITestContext context) {

        //GET by ID
        String newBookID = (String) context.getAttribute("ID");

        String getBookByIDResponse = given().queryParam("ID", newBookID)
                .when().get("/Library/GetBook.php")
                .then().assertThat().statusCode(200).extract().response().asString();

        //assert that it is an array and it is not empty
        //assert that the ID sent is the same id returned
        //assert that the book name returned is the same book expected

        System.out.println("GET BOOK BY ID RESPONSE: " + getBookByIDResponse);

    }

//    @Test
//    public void getBookByAuthorTest() {
//        //GET by Author name
//        String getBookAdded = given().queryParam("AuthorName", "Sandy P Doe")
//                .when().log().all().get("/Library/GetBook.php")
//                .then().log().all().assertThat().statusCode(200).extract().response().asString();
//
//
//        System.out.println("GET BOOK BY AUTHOR RESPONSE: " + getBookAdded);
//    }

    @Test(dependsOnMethods = {"addBookTest", "getBookByIDTest"},alwaysRun = true)
    @Story("Remove book from library")
    @Description("Verify that user can delete a book from library")
    public void deleteBookTest(ITestContext context) {

        String newBookID = (String) context.getAttribute("ID");

       given().header("Content-Type", "application/json")
                .body("{\n" +
                          "\"ID\":\""+newBookID+"\"\n" +
                        "}").when().post("/Library/DeleteBook.php")
                .then().log().all().assertThat().statusCode(200)
               .body("msg", containsString("deleted"))
               .extract().asString();
    }
}
