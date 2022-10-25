package Basics;

import Basics.Files.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Basics {
    public static void main(String[] args) throws IOException {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        // Validate if the Add place API is working
        // given - all input details
        // when - submit the API, resource, http method
        // then - validate the response
        String response = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(Files.readAllBytes(Paths.get("C:\\Users\\lkshm\\OneDrive\\Desktop\\Automation\\APIAutomation\\APIAutomation\\src\\main\\java\\Basics\\Files\\AddPlace.json") ))
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP")).extract().response().asString();
        System.out.println(response);
        // Add place, update place with new address, get place to validate if the new address is available
//        String place_id = ReusableMethods.get_data_from_json(response, "place_id");
//        String new_address = "70 winter walk, USA";
//        // Update place
//        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
//                .body(Payload.update_place(place_id, new_address))
//                .when().put("maps/api/place/update/json")
//                .then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
//        // Get place
//        String get_place_response = given().log().all().queryParam("key", "qaclick123")
//                .queryParam("place_id", place_id)
//                .when().get("/maps/api/place/get/json")
//                .then().assertThat().log().all().statusCode(200).extract().response().asString();
//        String actual_address = ReusableMethods.get_data_from_json(get_place_response,"address");
//        Assert.assertTrue(actual_address.equals(new_address));
    }
}
