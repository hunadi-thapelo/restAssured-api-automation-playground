package org.oauthtest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.pojo.Api;
import org.pojo.GetCourse;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class OAuthTest {

    public static void main(String[] args) {


        //POST METHOD
        String postResponse = given()
                .formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .formParams("grant_type","client_credentials")
                .formParams("scope", "trust")
                .when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();

//        System.out.println(postResponse);
        //EXAMPLE RESPONSE PRINTED TO CONSOLE: {"access_token":"ojLk4bnRyHfzuocuS9CtTQ==","token_type":"Bearer","expires_in":3600,"refresh_token":"NkzN6v5oZJ1XFMX0DD6hig==","scope":"create"}

        //use JsonPath to parse the response
        JsonPath jsPath = new JsonPath(postResponse); //we are parsing a String from the above response
        String accessToken = jsPath.getString("access_token"); //we want to get the String value of access_token key and we store it in a String variable


        //Once we have the accessToken, we want to access the CourseDetails which needs us to have the access token first

        //GET METHOD
        Response getResponse = given().queryParam("access_token", accessToken) //passing accessToken variable as it has the access token value stored
                .when()
                .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails");

        String responseBody = given().queryParam("access_token", accessToken) //passing accessToken variable as it has the access token value stored
                .when()
                .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").then().assertThat().statusCode(401)
                .extract().response().asString();

//        System.out.println(getResponse);
//        System.out.println("This is the GET response body: " + responseBody);

        //Test Requirement: Get API course details
        //java object variable so we can use the pojo get method in any
        GetCourse gc = given().queryParam("access_token", accessToken)
                .when()
                .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
                .as(GetCourse.class);

        System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());

        //Test Requirement: Dynamically Get SoapUI Webservices testing API course price details

        List<Api> apiCourses = gc.getCourses().getApi();

        for(int i = 0; i < gc.getCourses().getApi().size(); i++){

            if(apiCourses.get(i)
                    .getCourseTitle()
                    .equalsIgnoreCase("SoapUI Webservices testing")){
                System.out.println(apiCourses.get(i).getPrice());
            }

        }


    }
}
