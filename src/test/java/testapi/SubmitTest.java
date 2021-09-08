package testapi;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static tokensetup.GetToken.getToken;
import static tokensetup.GetToken.setURL;

public class SubmitTest {

    @Test
    public void submitTest() throws IOException {
        String url = setURL("/test/submit");

        String token = "Bearer " + getToken();

        String data = new String(Files.readAllBytes(Paths.get("/Users/hamza/RestAPIFramework/jsondata/submit.json")));

        RequestSpecification requestSpecification = given().body(data);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.header("Authorization", token);
        io.restassured.response.Response response = requestSpecification.post(url);

        String responseBody = response.asString();
        System.out.println(responseBody);

        JsonPath jsonPath = new JsonPath(responseBody);
        String actual = jsonPath.get("message");
        Assert.assertEquals(actual, "You are an Adult");

    }
}
