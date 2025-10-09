package org.jirademo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class AddJiraIssue {

    public static void main(String[] args){

        RestAssured.baseURI = "https://hunadee1.atlassian.net";

        String createBugIssueResponse = given().header("Content-Type", "application/json").header("Authorization","Basic aHVuYWRlZTFAZ21haWwuY29tOkFUQVRUM3hGZkdGME04WDdjdVAtMDRmS05VcDVUSGVudG4tUWU5azF4UHpMWGJncEpUdGM2aTdCamZJNkY5RE1tVWJmZ0p0am1GRFlLNWFNVGw3SXlLV09EeXRzVzRPN1pHbV9MU1hFMC04VHpKU1ZrZFpXSTR2YmNPWXNtSG51ZkZtMFM5aktwQm82dXZLWEJaMjBtdFhJSmVuYXh1N1JIXzFjaHhjZnluS2hyazIyT1JYUmV6MD1DQUM1RTgyOQ==")
                .body("{\n" +
                        "    \"fields\": {\n" +
                        "       \"project\":\n" +
                        "       {\n" +
                        "          \"key\": \"SCRUM\"\n" +
                        "       },\n" +
                        "       \"summary\": \"POST /customers response error message value empty \",\n" +
                        "       \"issuetype\": {\n" +
                        "          \"name\": \"Bug\"\n" +
                        "       }\n" +
                        "   }\n" +
                        "}")
                .when().post("/rest/api/3/issue")
                .then().log().all().assertThat().statusCode(201)
                .extract().response().asString();

        JsonPath js = new JsonPath(createBugIssueResponse);
        String issueId = js.getString("id");
        System.out.println(issueId);

    }
}
