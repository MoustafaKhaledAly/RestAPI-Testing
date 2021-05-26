import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.testng.Assert;

import org.testng.annotations.Test;



public class createAndUpdateUser {
    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;
    private static JSONObject json;
    private static    Response response;
    @Test()
    public void createUser() {
        requestSpec = RestAssured.given();
        requestSpec.header("Content-Type", "application/json");
         json = new JSONObject();
        json.put("name", "Moustafa");
        json.put("job", "Engineer");
        requestSpec.body(json.toJSONString());
         response = requestSpec.post("https://reqres.in/api/users");

        ResponseBody body = response.getBody();

        String bodyAsString = body.asString();
        System.out.println("Response Body is: " + bodyAsString);
        Assert.assertEquals(bodyAsString.contains("Moustafa") , true , "Response body contains Name");
        Assert.assertEquals(response.getStatusCode(), 201);
    }

    @Test()
    public void updateUser() {
        requestSpec = RestAssured.given();
        requestSpec.header("Content-Type", "application/json");
        json = new JSONObject();
        json.put("name", "omar");
        json.put("job", "lawyer");
        JsonPath jsonPath = new JsonPath(response.getBody().asString());
        int user_id = jsonPath.getInt("id");
        requestSpec.body(json.toJSONString());
         response = requestSpec.put("https://reqres.in/api/users/"+user_id);
        ResponseBody body = response.getBody();
        String bodyAsString = body.asString();
        System.out.println("Response Body is: " + bodyAsString);
       Assert.assertEquals(bodyAsString.contains("omar") , true , "Response body contains Name");
        Assert.assertEquals(response.getStatusCode(), 200);

    }


}






