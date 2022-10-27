package E2ETestcases;

import Basics.ReusableMethods;
import E2ETestcases.POJO.LoginResponse;
import E2ETestcases.POJO.OrderDetails;
import E2ETestcases.POJO.Orders;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class EcommeceAPI {
    public static void main(String[] args) {
        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").setContentType(ContentType.JSON).build();
        LoginRequest login = new LoginRequest();
        login.setUserEmail("lkshm97@gmail.com");
        login.setUserPassword("Test1234");
        RequestSpecification loginReq = given().relaxedHTTPSValidation().spec(requestSpecification).body(login);
        LoginResponse loginResponse = loginReq.when().post("/api/ecom/auth/login")
                .then().extract().response().as(LoginResponse.class);
        //Adding Product
        RequestSpecification add_prod_spec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
                .addHeader("Authorization", loginResponse.getToken()).build();
        RequestSpecification add_prod_req_spec = given().spec(add_prod_spec)
                .param("productName", "qwerty")
                .param("productAddedBy", loginResponse.getUserId())
                .param("productCategory", "fashion")
                .param("productSubCategory", "shirts")
                .param("productPrice", "11500")
                .param("productDescription", "Addias Originals")
                .param("productFor", "women")
                .multiPart("productImage", new File("C:\\Users\\lkshm\\OneDrive\\Desktop\\Automation\\APIAutomation\\APIAutomation\\productImage_1650649434146.jpeg"));
        String add_prod_resp = add_prod_req_spec.when().post("/api/ecom/product/add-product")
                .then().extract().asString();
        String product_id = ReusableMethods.get_data_from_json(add_prod_resp, "productId");
        //Place an order
        RequestSpecification create_order_spec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").setContentType(ContentType.JSON)
                .addHeader("Authorization", loginResponse.getToken()).build();
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setProductOrderedId(product_id);
        orderDetails.setCountry("India");
        Orders orders = new Orders();
        List<OrderDetails> orderDetailsArrays = Arrays.asList(orderDetails);
        orders.setOrders(orderDetailsArrays);
        RequestSpecification create_order_req = given().spec(create_order_spec).body(orders);
        create_order_req.when().post("/api/ecom/order/create-order")
                .then().extract().response().asString();
        RequestSpecification deleteProdBaseReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").setContentType(ContentType.JSON)
                .addHeader("Authorization", loginResponse.getToken()).build();
        String delete_response = given().spec(deleteProdBaseReq).pathParam("productId", product_id)
                .when().delete("/api/ecom/product/delete-product/{productId}")
                .then().extract().asString();
    }
}
