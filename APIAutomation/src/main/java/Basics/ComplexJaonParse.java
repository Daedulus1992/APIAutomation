package Basics;

import Basics.Files.Payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class ComplexJaonParse {

    public static void main(String[] args) {
        JsonPath js = new JsonPath(Payload.coursePrice());
        //1. Print No of courses returned by API
        int size = js.getInt("courses.size()");
        System.out.println(size);
        //2.Print Purchase Amount
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println("Purchase amount: "+purchaseAmount);
        //3. Print Title of the first course
        String first_title = js.getString("courses[0].title");
        System.out.println("First course title: " + first_title);
        //4. Print All course titles and their respective Prices
        for (int i = 0; i < size; i++)
            System.out.println(js.getString("courses["+i+"].title") +": "+js.getString("courses["+i+"].price"));
        //5. Print no of copies sold by RPA Course
        for (int i = 0; i < size; i++)
            if(js.getString("courses["+i+"].title").equalsIgnoreCase("RPA")) {
                System.out.println(js.getString("courses[" + i + "].title") + ": " + js.getInt("courses[" + i + "].copies"));
                break;
            }
        //6. Verify if Sum of all Course prices matches with Purchase Amount
        int sum = 0;
        for (int i = 0; i < size; i++)
            sum += js.getInt("courses[" + i + "].price") * js.getInt("courses[" + i + "].copies");
        Assert.assertEquals(sum, purchaseAmount);
    }
}
