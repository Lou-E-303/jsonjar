package example;

import jsonjar.lexing_parsing.JsonParser;
import jsonjar.json_objects.*;
import jsonjar.printing.JsonPrettyPrinter;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

class ExampleUsage {
    public static void main(String[] args) throws IOException {
        JsonParser parser = new JsonParser();

        // Parse JSON from a string
        String jsonString = """
            {
                "name": "Alice",
                "age": 30,
                "active": true,
                "scores": [95, 87, 92],
                "address": {
                    "city": "London",
                    "postcode": "SW1A 1AA"
                }
            }
            """;
        Json result = parser.parseFromString(jsonString);

        // Parse JSON from a file
        File jsonFile = new File("src/main/java/example/data.json");
        Json fileResult = parser.parseFromFile(jsonFile);

        // Access values easily
        JsonObject obj = (JsonObject) result;
        String name = obj.getAsString("name");
        BigDecimal age = obj.getAsBigDecimal("age");
        boolean active = obj.getAsBoolean("active");

        // Access nested objects
        String city = obj.getAsJsonObject("address").getAsString("city");

        // Work with arrays
        JsonArray scores = obj.getAsJsonArray("scores");
        for (Json score : scores) {
            System.out.println(((JsonNumber) score).getValue());
        }

        // Or access array elements directly
        BigDecimal firstScore = scores.getAsBigDecimal(0);
        BigDecimal secondScore = scores.getAsBigDecimal(1);

        // Pretty print the result
        JsonPrettyPrinter printer = new JsonPrettyPrinter();
        String formatted = printer.getFormattedJsonString(result, 0);
        System.out.println(formatted);
    }
}