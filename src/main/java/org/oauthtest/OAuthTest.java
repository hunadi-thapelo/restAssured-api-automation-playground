package org.oauthtest;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class OAuthTest {

    public static void main(String[] args) {


        //POST METHOD
       String response = given()
                .formParams("client_id", "")
                .formParams("client_secret", "")
                .formParams("grant_type","client_credentials")
                .formParams("scope", "trust")
                .when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();

        System.out.println(response);

        //use JsonPath to parse the response
        JsonPath jsPath = new JsonPath(response); //we are parsing a String from the above response
        String accessToken = jsPath.getString("access_token"); //we want to get the String value of access_token key and we store it in a String variable


        //Once we have the accessToken, we want to access the CourseDetails which needs us to have the access token first

        //GET METHOD
       String getResponse = given().queryParam("access_token", accessToken) //passing accessToken variable as it has the access token value stored
                .when().log().all()
                .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();

        System.out.println(getResponse);
    }
}
