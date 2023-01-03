package org.basics;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class BasicsDemo {
    public static void main(String[] args){

        //Test case: Validate if Add Place api is working as expected

        //First set your base URL
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").
                body("{\n" +
                        "  \"location\": {\n" +
                        "    \"lat\": -38.383494,\n" +
                        "    \"lng\": 33.427362\n" +
                        "  },\n" +
                        "  \"accuracy\": 50,\n" +
                        "  \"name\": \"Pattys Dance Studio\",\n" +
                        "  \"phone_number\": \"(+27) 983 0917\",\n" +
                        "  \"address\": \"29, Steam Avenue, Johannesburg 2022\",\n" +
                        "  \"types\": [\n" +
                        "    \"shoe park\",\n" +
                        "    \"shop\"\n" +
                        "  ],\n" +
                        "  \"website\": \"http://google.com\",\n" +
                        "  \"language\": \"French-IN\"\n" +
                        "}").when().post("maps/api/place/add/json").then().assertThat().statusCode(200).
                log().all();



    }
}
