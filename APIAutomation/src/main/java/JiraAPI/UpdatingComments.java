package JiraAPI;

import Basics.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UpdatingComments {
    @Test
    public void updateComment(){
        String expectedComment = "Hi!, How are you!";
        RestAssured.baseURI = "http://localhost:8080";
        SessionFilter sessionFilter = new SessionFilter();
        given().relaxedHTTPSValidation().header("Content-Type", "application/json").body("{\n" +
                "    \"username\": \"lkshm97\",\n" +
                "    \"password\": \"Laks@1992\"\n" +
                "}").filter(sessionFilter).when().post("/rest/auth/1/session")
                        .then().assertThat().statusCode(200);
        //10007  /rest/api/2/issue/10007/comment
        String addCommentResponse = given().pathParams("id", "10007").header("Content-Type", "application/json").body("{\n" +
                "    \"body\": \""+expectedComment+"\",\n" +
                "    \"visibility\": {\n" +
                "        \"type\": \"role\",\n" +
                "        \"value\": \"Administrators\"\n" +
                "    }\n" +
                "}")
                .filter(sessionFilter).when().post("/rest/api/2/issue/{id}/comment")
                .then().statusCode(201).extract().response().asString();
        String comment_ID = ReusableMethods.get_data_from_json(addCommentResponse, "id");
        //Add attachments
        given().header("X-Atlassian-Token", "no-check").pathParams("id", "10007")
                .header("Content-Type", "multipart/form-data").filter(sessionFilter)
                .multiPart("file", new File("JiraUploadFile.txt"))
                .when().post("/rest/api/2/issue/{id}/attachments")
                .then().assertThat().statusCode(200);
        String response = given().filter(sessionFilter).pathParams("id", "10007").queryParam("fields", "comment")
                .when().get("/rest/api/2/issue/{id}")
                .then().extract().response().asString();
        int total_comments = ReusableMethods.get_data_from_json_in_int(response, "fields.comment.comments.size()");
        String last_comment_id = ReusableMethods.get_data_from_json(response, "fields.comment.comments["+(total_comments - 1)+"].id");
        String last_comment = ReusableMethods.get_data_from_json(response, "fields.comment.comments["+(total_comments - 1)+"].body");
        Assert.assertEquals(last_comment_id, comment_ID);
        Assert.assertEquals(last_comment, expectedComment);
    }
}
