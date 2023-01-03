package org.files;

public class payload {

    public static String AddPlace()
    {
        return"{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Pikachu Dance Studio\",\n" +
                "  \"phone_number\": \"(+27) 983 0917\",\n" +
                "  \"address\": \"29, Steam Avenue, Johannesburg 2022\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://google.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}";
    }
}
