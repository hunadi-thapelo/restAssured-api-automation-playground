package org.files;

import io.restassured.path.json.JsonPath;

public class ReusableMethod {

    public static JsonPath rawToJson(String response) //make it static to return as class.method
    {
        JsonPath jsonP = new JsonPath(response);
        return jsonP;
    }
}
