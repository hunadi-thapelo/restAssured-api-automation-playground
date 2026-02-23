package org.datadrivendemo;

import java.io.IOException;
import java.util.ArrayList;

public class ExcelDataDrivenTestDemo {

    //can use the exceldatadriven utility to inject test data in any framework e.g Selenium, Appium, RestAssured
    //e.g driver.findElement(By.id("idname").sendKeys(testData.get(0))

    public static void main(String[] args) throws IOException {
        ExcelDataDriven test = new ExcelDataDriven();

        ArrayList<String> testData = test.getData("AddnBook","restanssured");
        System.out.println(testData);

    }


}
