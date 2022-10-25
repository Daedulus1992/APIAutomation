package Basics;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
    public static String get_data_from_json(String response, String required){
        JsonPath js = new JsonPath(response);
        return js.getString(required);
    }
    public static int get_data_from_json_in_int(String response, String required){
        JsonPath js = new JsonPath(response);
        return js.getInt(required);
    }
}
