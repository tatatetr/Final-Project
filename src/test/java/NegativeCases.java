import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NegativeCases {

    // #1


    @Test(priority = 1)


    public void postData() {

        RestAssured.baseURI = "https://petstore.swagger.io";

        LombokData postData = new LombokData();

        postData.setId("text");
        postData.setPetId("2");
        postData.setQuantity("1");
        postData.setShipDate("2025-02-04T19:11:06.016Z");
        postData.setStatus("active");


        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(postData)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .post("/v2/store/order")
                .then()
                .log().all()
                .extract().response();

        System.out.println(response.statusCode());
        System.out.println(response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 500, "Check if status code is 500");
        Assert.assertEquals(response.jsonPath().getString("message"), "something bad happened", "Check status body text");

    }


    // #2


    @Test(priority = 2)


    public void testNegativeCase() {

        RestAssured.baseURI = "https://petstore.swagger.io";

        LombokData postData = new LombokData();

        postData.setId("9");
        postData.setPetId("text");
        postData.setQuantity("1");
        postData.setShipDate("2025-02-04T19:11:06.016Z");
        postData.setStatus("active");


        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(postData)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .post("/v2/store/order")
                .then()
                .log().all()
                .extract().response();

        System.out.println(response.statusCode());
        System.out.println(response.getBody().asString());


        Assert.assertEquals(response.getStatusCode(), 500, "Check if status code is 500");

        Assert.assertEquals(response.jsonPath().getString("message"), "something bad happened", "Check status body text");
    }


    // #3


    @Test(priority = 3)


    public void NegativeCase() {

        RestAssured.baseURI = "https://petstore.swagger.io";

        LombokData postData = new LombokData();

        postData.setId("9");
        postData.setPetId("2");
        postData.setQuantity("text");
        postData.setShipDate("2025-02-04T19:11:06.016Z");
        postData.setStatus("active");


        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(postData)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .when()
                .post("/v2/store/order")
                .then()
                .log().all()
                .extract().response();

        System.out.println(response.statusCode());
        System.out.println(response.getBody().asString());


        Assert.assertEquals(response.getStatusCode(), 500, "Check if status code is 500");

        Assert.assertEquals(response.jsonPath().getString("message"), "something bad happened", "Check status body text");


    }

}
