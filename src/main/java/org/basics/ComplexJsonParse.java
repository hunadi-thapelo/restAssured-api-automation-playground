package org.basics;

import io.restassured.path.json.JsonPath;
import org.files.payload;

public class ComplexJsonParse {

    public static void main (String[] args)
    {
        JsonPath js = new JsonPath(payload.coursePrice());

        //1. Print No of courses returned by API
        int courseCount = js.getInt("courses.size()");
        System.out.println(courseCount);

        //2.Print Purchase Amount
        int totalAmount = js.getInt("dashboard.purchaseAmount"); //traverse parent to child
        System.out.println(totalAmount);

    }
}
