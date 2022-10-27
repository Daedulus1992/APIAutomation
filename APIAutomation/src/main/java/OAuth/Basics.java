package OAuth;

import Serialization.POJO.GetCourse;
import Serialization.POJO.WebAutomation;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class Basics {
    // What is OAuth 2.0
    // Oauth 2.0 comes with multiple Grant type
    // Authorization code and client credentials are the most commonly used Grant types for OAuth
    // Authorization code grant type
    public static void main(String[] args) {
//        RestAssured.baseURI = "https://rahulshettyacademy.com/getCourse.php";
        String URL = "https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2FvAHBQUZU6o4WJ719NrGBzSELBFVBI9XbxvOtYpmYpeV47bFVExkaxWaF_XR14PHtTZf7ILSEeamywJKwo_BYs9M&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&session_state=0c32992f0d47e93d273922018ade42d1072b9d1f..a35c&prompt=none#";
//        String code = URL.split("code=")[1].split("&scope=")[0];
//        System.out.println(code);
//        String access_token_response = given().queryParams("code", code).urlEncodingEnabled(false)
//                .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
//                .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
//                .queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
//                .queryParams("grant_type", "authorization_code")
//                .when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
//        String access_token = new JsonPath(access_token_response).getString("access_token");
        String access_token = "ya29.a0Aa4xrXPfwhuI2-HTehRHseda0j5S37C1jFKHIz4eTs9ue95-Fmu2fXUN-a3wyAlkwxlu8_1ssciw2jnYEiakcT0Td-dV1tYgtIApKSkVR2imTKWrv6ccNYgjvg6nPsYgIDnga361qX7HDrSgtWHL9pGdWIp4GwaCgYKATASARASFQEjDvL9cKIGmKWBsw6CYLGRyQWXFQ0165";
        GetCourse raw_response = given().queryParam("access_token", access_token).expect().defaultParser(Parser.JSON)
                .when().get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
        System.out.println(raw_response.getLinkedIn());
        System.out.println(raw_response.getCourses().getApi().get(1).getCourseTitle());
        System.out.println(raw_response.getCourses().getApi().stream().filter(s -> s.getCourseTitle().contains("Soap")).collect(Collectors.toList()).get(0).getPrice());
        List<String> courses = Arrays.asList("Selenium Webdriver Java", "Cypress", "Protractor");
        raw_response.getCourses().getWebAutomation().stream().forEach(element -> System.out.println(element.getCourseTitle()));
    }
}
