import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Scenario2 {


    //#1

    @DataProvider(name = "postData")
    public Object[][] createUser() {
        return new Object[][]{
                {"9", "oliver", "Oliver", "Williams", "oliver@gmail.com", "Password123@", "992342312", "1"},

        };

    }


    @Test(dataProvider = "postData", priority = 1)


    public void postData(String id, String username, String firstName, String lastName, String email, String password, String phone, String userStatus) {

        RestAssured.baseURI = "https://petstore.swagger.io";
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", id);
        requestBody.put("username", username);
        requestBody.put("firstName", firstName);
        requestBody.put("lastName", lastName);
        requestBody.put("email", email);
        requestBody.put("password", password);
        requestBody.put("phone", phone);
        requestBody.put("userStatus", userStatus);


        Response response = RestAssured
                .given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .when()
                .post("/v2/user")
                .then()
                .extract().response();

        System.out.println(response.statusCode());
        System.out.println(response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200, "Check if status code is 200");

    }

    // #2


    @DataProvider(name = "getData")
    public Object[][] getUser() {
        return new Object[][]{
                {"9", "oliver", "Oliver", "Williams", "oliver@gmail.com", "Password123@", "992342312", "1"},

        };

    }


    @Test(dataProvider = "postData", priority = 2)


    public void getData(String id, String username, String firstName, String lastName, String email, String password, String phone, String userStatus) {

        RestAssured.baseURI = "https://petstore.swagger.io";
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", id);
        requestBody.put("username", username);
        requestBody.put("firstName", firstName);
        requestBody.put("lastName", lastName);
        requestBody.put("email", email);
        requestBody.put("password", password);
        requestBody.put("phone", phone);
        requestBody.put("userStatus", userStatus);


        Response response = RestAssured
                .given()
                .pathParam("username", "oliver")
                .when()
                .get("/v2/user/{username}")
                .then()
                .extract().response();

        System.out.println(response.statusCode());
        System.out.println(response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200, "Check if status code is 200");

        Assert.assertEquals(response.jsonPath().getString("username"), username, "username is relevant in response");
        Assert.assertEquals(response.jsonPath().getString("firstName"), firstName, "firstName is relevant in response");
        Assert.assertEquals(response.jsonPath().getString("lastName"), lastName, "lastName is relevant in response");
        Assert.assertEquals(response.jsonPath().getString("email"), email, "email is relevant in response");
        Assert.assertEquals(response.jsonPath().getString("password"), password, "password is relevant in response");
        Assert.assertEquals(response.jsonPath().getString("phone"), phone, "phone is relevant in response");
        Assert.assertEquals(response.jsonPath().getString("userStatus"), userStatus, "userStatus is relevant in response");


    }


//#3


    @DataProvider(name = "putData")
    public Object[][] putData() {
        return new Object[][]{
                {"9", "john", "Oliver", "Williams", "oliver@gmail.com", "Password123@", "992342312", "1"},

        };

    }


    @Test(dataProvider = "putData", priority = 3)


    public void putData(String id, String username, String firstName, String lastName, String email, String password, String phone, String userStatus) {

        RestAssured.baseURI = "https://petstore.swagger.io";
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", id);
        requestBody.put("username", username);
        requestBody.put("firstName", firstName);
        requestBody.put("lastName", lastName);
        requestBody.put("email", email);
        requestBody.put("password", password);
        requestBody.put("phone", phone);
        requestBody.put("userStatus", userStatus);


        Response response = RestAssured
                .given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .pathParam("username", "john")
                .body(requestBody.toString())
                .when()
                .put("/v2/user/{username}")
                .then()
                .extract().response();

        System.out.println(response.statusCode());
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 200, "Check if status code is 200");

    }

    // #4
    @DataProvider(name = "checkData")
    public Object[][] checkData() {
        return new Object[][]{
                {"9", "john", "Oliver", "Williams", "oliver@gmail.com", "Password123@", "992342312", "1"},

        };

    }


    @Test(dataProvider = "checkData", priority = 4)


    public void checkData(String id, String username, String firstName, String lastName, String email, String password, String phone, String userStatus) {

        RestAssured.baseURI = "https://petstore.swagger.io";
        JSONObject requestBody = new JSONObject();
        requestBody.put("id", id);
        requestBody.put("username", username);
        requestBody.put("firstName", firstName);
        requestBody.put("lastName", lastName);
        requestBody.put("email", email);
        requestBody.put("password", password);
        requestBody.put("phone", phone);
        requestBody.put("userStatus", userStatus);


        Response response = RestAssured
                .given()
                .pathParam("username", "john")
                .when()
                .get("/v2/user/{username}")
                .then()
                .extract().response();

        System.out.println(response.statusCode());
        System.out.println(response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200, "Check if status code is 200");

        Assert.assertEquals(response.jsonPath().getString("username"), username, "username is relevant in response");
        Assert.assertEquals(response.jsonPath().getString("firstName"), firstName, "firstName is relevant in response");
        Assert.assertEquals(response.jsonPath().getString("lastName"), lastName, "lastName is relevant in response");
        Assert.assertEquals(response.jsonPath().getString("email"), email, "email is relevant in response");
        Assert.assertEquals(response.jsonPath().getString("password"), password, "password is relevant in response");
        Assert.assertEquals(response.jsonPath().getString("phone"), phone, "phone is relevant in response");
        Assert.assertEquals(response.jsonPath().getString("userStatus"), userStatus, "userStatus is relevant in response");

    }
    // #5


    @DataProvider(name = "logIn")
    public Object[][] logIn() {
        return new Object[][]{
                {"john", "Password123@"},

        };

    }


    @Test(dataProvider = "logIn", priority = 5)


    public void logIn(String username, String password) {

        RestAssured.baseURI = "https://petstore.swagger.io";
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", username);
        requestBody.put("password", password);


        Response response = RestAssured
                .given()
                .header("accept", "application/json")
                .queryParams("username", username)
                .queryParams("password ", password)
                .body(requestBody.toString())
                .when()
                .get("/v2/user/login")
                .then()
                .extract().response();

        System.out.println(response.statusCode());
        System.out.println(response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200, "Check if status code is 200");

        Assert.assertNotNull(response.jsonPath().getString("message"), "Message data is visible in response body");


    }


    //#6

    @Test(priority = 6)
    public void logOut() {

        RestAssured.baseURI = "https://petstore.swagger.io";
        JSONObject requestBody = new JSONObject();
        Response response = RestAssured

                .given()
                .header("accept", "application/json")
                .when()
                .get("/v2/user/logout")
                .then()
                .extract().response();

        System.out.println(response.statusCode());
        System.out.println(response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200, "Check if status code is 200");
        Assert.assertEquals(response.jsonPath().getString("message"), "ok", "check message");


    }

    //#7

    @DataProvider(name = "deleteUser")
    public Object[][] deleteUser() {
        return new Object[][]{
                {"john"},

        };

    }


    @Test(dataProvider = "deleteUser", priority = 7)


    public void deleteUser(String username) {

        RestAssured.baseURI = "https://petstore.swagger.io";
        JSONObject requestBody = new JSONObject();
        requestBody.put("username", "john");

        Response response = RestAssured
                .given()
                .pathParam("username", "john")
                .when()
                .delete("/v2/user/{username}")
                .then()
                .extract().response();

        System.out.println(response.statusCode());
        System.out.println(response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200, "Check if status code is 200");

        Assert.assertEquals(response.jsonPath().getString("message"), "john", "check message");
    }


}