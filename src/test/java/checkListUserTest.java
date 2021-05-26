import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class checkListUserTest {
    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;
    @BeforeClass
    public static void createRequestSpecification() {

        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://reqres.in/api/users").
                build();
        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(201).
                build();
    }
    @DataProvider
    public static Object[][] userName() {
        return new Object[][] {
                { 0,"1","George"},
                { 2,"2","Emma"},
        };
    }
    @Test (dataProvider ="userName")
    public void createUser(int userNum ,String pageNum,String expectedFirstName)
    {

        given().

                when().
                get("https://reqres.in/api/users").
                then().
                assertThat().
              body("data["+userNum+"].first_name", equalTo(expectedFirstName));

    }
}
