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

        //3. Print Title of the first course
        String titleFirstCourse = js.get("courses[0].title");
        System.out.println(titleFirstCourse);

        //4. Print All course titles and their respective Prices
        for(int i=0;i<courseCount;i++)
        {
            String title = js.get("courses["+i+"].title");
            System.out.println(title);
            System.out.println(js.get("courses["+i+"].price").toString());
        }

        //5. Print no of copies sold by RPA Course
        System.out.println("Print no of copies sold by RPA Course");
        for(int i=0;i<courseCount;i++)
        {
            String title = js.get("courses["+i+"].title");
            if(title.equalsIgnoreCase("RPA")){
               int numOfCopies = js.get("courses["+i+"].copies");
                System.out.println(numOfCopies);
                break;
            }
        }

    }
}
