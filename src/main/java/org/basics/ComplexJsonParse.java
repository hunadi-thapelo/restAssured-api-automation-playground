package org.basics;

import io.restassured.path.json.JsonPath;
import org.files.payload;

public class ComplexJsonParse {

    public static void main (String[] args)
    {
        JsonPath js = new JsonPath(payload.coursePrice());

    }
}
