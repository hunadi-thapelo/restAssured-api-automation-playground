package org.datadriven;

import io.restassured.RestAssured;

public class LibraryAPIBaseURIConfig {

    public static final String BASE_URL = "http://216.10.245.166";

    //create config setup method
    public static void setup() {
        RestAssured.baseURI = BASE_URL; // assign the restAssured String variable to the test api base url
    }

}
