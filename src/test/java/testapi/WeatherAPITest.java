package testapi;

import io.restassured.path.json.JsonPath;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static tokensetup.GetToken.setURL;

public class WeatherAPITest {

    @Test
    public void weatherTest() throws IOException {
        String url = setURL("/current?access_key=3bec97ab5239d23224bf6804fa5c2a47&query=New York");

        OkHttpClient client = new OkHttpClient.Builder().build();

        Request request = new Request.Builder()
                .url(url)
                .method("GET", (RequestBody) null)
                .build();

        Response response = client.newCall(request).execute();

        String responseBody = response.body().string();
        System.out.println(responseBody);

        Assert.assertNotNull(responseBody);

    }

}
