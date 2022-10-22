package Basics;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
    public static String get_data_from_json(String response, String required){
        JsonPath js = new JsonPath(response);
        return js.getString(required);
    }
}
