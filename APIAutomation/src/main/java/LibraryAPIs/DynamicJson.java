package LibraryAPIs;

import Basics.Files.Payload;
import Basics.ReusableMethods;
import io.restassured.RestAssured;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson {
    @Test(dataProvider = "BooksData")
    public void addBookAPI(String ISBN, String IsleNumber){
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().header("Content-Type", "application/json").body(Payload.addBook(ISBN, IsleNumber))
                .when().post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();
        String id = ReusableMethods.get_data_from_json(response, "ID");
        System.out.println(id);
    }
    @DataProvider(name = "BooksData")
    public Object[][] getData(){
        return new Object[][]{{"uutyt002", "224200011"}, {"uutyt003", "224200012"}, {"uutyt004", "224200013"}};
    }
}
