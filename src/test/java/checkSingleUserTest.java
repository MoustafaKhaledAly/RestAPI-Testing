import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class checkSingleUserTest {
    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;
    @BeforeClass
    public static void createRequestSpecification() {

        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://reqres.in/api/users/").
                build();
        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                build();
    }
    @DataProvider
    public static Object[][] userName() {
        return new Object[][] {
                { "2","Janet"},
                { "3","Emma"},
                { "4","Eve"}
        };
    }
    @Test (dataProvider ="userName")
    public void getSingleUserData(String userId,String expectedFirstName)
    {
        given().
                spec(requestSpec).
                pathParam("userId", userId).
                when().
                get("{userId}").
                then().
                spec(responseSpec).
                and().
                assertThat().
                body("data.'first_name'", equalTo(expectedFirstName));
    }
}
