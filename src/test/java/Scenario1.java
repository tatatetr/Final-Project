import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class Scenario1 {

//#1

    @DataProvider(name = "postData")
    public Object[][] createOrder() {
        return new Object[][]{
                {"9", "2", "1", "2025-02-04T19:11:06.016Z", "active"},

        };

    }


    @Test(dataProvider = "postData", priority = 1)


    public void postData(String id, String petId, String quantity, String shipDate, String status) {

        RestAssured.baseURI = "https://petstore.swagger.io";
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", id);
        requestBody.put("petId", petId);
        requestBody.put("quantity", quantity);
        requestBody.put("shipDate", shipDate);
        requestBody.put("status", status);


        Response response = RestAssured
                .given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .when()
                .post("/v2/store/order")
                .then()
                .extract().response();

        System.out.println(response.statusCode());
        System.out.println(response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200, "Check if status code is 200");

        Assert.assertEquals(response.jsonPath().getString("id"), id, "id is relevant in response");
        Assert.assertEquals(response.jsonPath().getString("petId"), petId, "petId is relevant in response");
        Assert.assertEquals(response.jsonPath().getString("quantity"), quantity, "Quantity is relevant in response");
        Assert.assertEquals(response.jsonPath().getString("status"), status, "Status is relevant in response");

    }

    //#2


    @DataProvider(name = "getData")
    public Object[][] getData() {
        return new Object[][]{
                {"9", "2", "1", "2025-02-04T19:11:06.016Z", "active"},

        };

    }



    @Test(dataProvider = "getData", priority = 2)


    public void getData(String id, String petId, String quantity, String shipDate, String status) {

        RestAssured.baseURI = "https://petstore.swagger.io";
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", id);
        requestBody.put("petId", petId);
        requestBody.put("quantity", quantity);
        requestBody.put("shipDate", shipDate);
        requestBody.put("status", status);


        Response response = RestAssured
                .given()
                .pathParam("orderId", 9)
                .when()
                .get("/v2/store/order/{orderId}")
                .then()
                .extract().response();

        System.out.println(response.statusCode());
        System.out.println(response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200, "Check if status code is 200");

        Assert.assertEquals(response.jsonPath().getString("id"), id, "id is relevant in response");
        Assert.assertEquals(response.jsonPath().getString("petId"), petId, "petId is relevant in response");
        Assert.assertEquals(response.jsonPath().getString("quantity"), quantity, "Quantity is relevant in response");
        Assert.assertEquals(response.jsonPath().getString("status"), status, "Status is relevant in response");


    }

//#3

    @Test(priority = 3)
    public void deleteData() {

        RestAssured.baseURI = "https://petstore.swagger.io";

        int orderId = 9;

        Response response = given()
                .header("accept", "application/json")
                .pathParam("orderId", orderId)
                .when()
                .delete("/v2/store/order/{orderId}")
                .then()
                .extract().response();

        System.out.println(response.statusCode());
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200, "Check if status code is 200");


    }

//#4


    @Test(priority = 4)
    public void deleteOrder() {

        RestAssured.baseURI = "https://petstore.swagger.io";

        int orderId = 9;

        Response response = given()
                .header("accept", "application/json")
                .pathParam("orderId", orderId)
                .when()
                .delete("/v2/store/order/{orderId}")
                .then()
                .extract().response();

        System.out.println(response.statusCode());
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 404, "Check if status code is 404");
        String errorResponse = response.jsonPath().getString("message");
        String errorResponseText = "Order Not Found";
        System.out.println(errorResponse);
        Assert.assertEquals(errorResponse, errorResponseText, "Check error message");


    }


    //#5


    @Test(dataProvider = "getData", priority = 5)


    public void getOrder(String id, String petId, String quantity, String shipDate, String status) {

        RestAssured.baseURI = "https://petstore.swagger.io";
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", id);
        requestBody.put("petId", petId);
        requestBody.put("quantity", quantity);
        requestBody.put("shipDate", shipDate);
        requestBody.put("status", status);


        Response response = given()
                .pathParam("orderId", 9)
                .when()
                .get("/v2/store/order/{orderId}")
                .then()
                .extract().response();

        System.out.println(response.statusCode());
        System.out.println(response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 404, "Check if status code is 404");


        String errorResponse = response.jsonPath().getString("message");
        String errorResponseText = "Order not found";
        System.out.println(errorResponse);
        Assert.assertEquals(errorResponse, errorResponseText, "Check error message");


    }


}