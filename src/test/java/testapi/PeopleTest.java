package testapi;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static tokensetup.GetToken.getToken;
import static tokensetup.GetToken.setURL;

public class PeopleTest {

    @Test
    public void getPeople() throws IOException {
        String url = setURL("/test/people");

        String token = "Bearer " + getToken();

        io.restassured.response.Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .get(url);

        String resp = response.asString();
        System.out.println(resp);

        JsonPath jsonPath = new JsonPath(resp);
        String jsonResponseBody = jsonPath.get("body");

        JsonPath jsonPath2 = new JsonPath(jsonResponseBody);
        String actual = jsonPath2.get("name");
        System.out.println(actual);
        Assert.assertEquals(actual, "Luke Skywalker");

    }


}
