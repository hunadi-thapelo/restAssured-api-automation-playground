package org.basics;

import io.restassured.path.json.JsonPath;
import org.files.payload;
import org.testng.annotations.Test;

public class SumValidation {

    @Test
    public void sumOfCourses()
    {
        JsonPath js = new JsonPath(payload.coursePrice());
        int courseCount = js.getInt("courses.size()");
        int totalSum = 0;
        for(int i=0;i<courseCount;i++)
        {

            int price = js.getInt("courses["+i+"].price");
            int copies = js.getInt("courses["+i+"].copies");
            int amount = price * copies;
            System.out.println(amount);
            totalSum = totalSum + amount;

        }
        System.out.println("Total sum " + totalSum);
    }
}
