package Basics.OAuth;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

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
        String access_token = "ya29.a0Aa4xrXPCQxVNJalrsPpmOV_4LFk4PkVedNK8DC2kA9hnzfIzrY-12XFNHmTxB0XN0fTVBFPIGrfECuQxL0ZuZZRLI-twhHCT19AUfUzxSzozGVPBG8pUVYvhpPVRw80oHX9Xf3SX2uu27-3yp6eHUyWx7MlQaCgYKATASARISFQEjDvL9uN6LeWMRP66QI-v-DWZcdA0163";
        String raw_response = given().queryParam("access_token", access_token)
                .when().get("https://rahulshettyacademy.com/getCourse.php").asString();
        System.out.println(raw_response);
    }
}
