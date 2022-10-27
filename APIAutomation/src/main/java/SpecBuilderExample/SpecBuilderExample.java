package SpecBuilderExample;

import Basics.GoogleMapsAPI.Pojo.AddPlacePOJO;
import Basics.GoogleMapsAPI.Pojo.Location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SpecBuilderExample {
    @Test
    public void serialized_test(){
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        // Validate if the Add place API is working
        // given - all input details
        // when - submit the API, resource, http method
        // then - validate the response
        RequestSpecification reqSpec = new RequestSpecBuilder().addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON).build();
        ResponseSpecification resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectBody("scope", equalTo("APP")).build();
        String response = given().log().all().spec(reqSpec)
                .body(getData())
                .when().post("maps/api/place/add/json")
                .then().assertThat().spec(resSpec).extract().response().asString();
        System.out.println(response);
    }

    public AddPlacePOJO getData(){
        AddPlacePOJO addPlacePOJO = new AddPlacePOJO();
        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        addPlacePOJO.setLocation(location);
        addPlacePOJO.setAccuracy(50);
        addPlacePOJO.setName("Frontline house");
        addPlacePOJO.setPhone_number("(+91) 983 893 3937");
        addPlacePOJO.setAddress("29, side layout, cohen 09");
        addPlacePOJO.setTypes(Arrays.asList("shoe park", "shoe"));
        addPlacePOJO.setWebsite("http://google.com");
        addPlacePOJO.setLanguage("French-IN");
        return addPlacePOJO;
    }
}
