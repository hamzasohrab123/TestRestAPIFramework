package tokensetup;

import io.restassured.path.json.JsonPath;
import okhttp3.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Base64;

public class GetToken {

    private static Logger log = LogManager.getLogger(GetToken.class.getName());

    public static final String hostName = "https://5x9m5ed0tj.execute-api.us-east-1.amazonaws.com";
//    public static final String hostName = "/current?access_key=3bec97ab5239d23224bf6804fa5c2a47&query=New York";

    public static String setURL(String endpoint) {
        String url = hostName + endpoint;

        log.info("Enpoint: " + endpoint);

        return url;

    }

    public static String getToken() throws IOException {
        String url = "https://izaan-test.auth.us-east-1.amazoncognito.com/oauth2/token";

        OkHttpClient client = new OkHttpClient.Builder().build();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");

        RequestBody body = RequestBody.create(mediaType, "scope=izaan_test/post_info&grant_type=client_credentials");

        String encode = Base64.getEncoder().encodeToString(("1u5io4va9sr45n79fceg2damjf:1qbkthvp7lbc7aavuhhmfg8f2crekor9h2h7abu2oru1nlpj71fe").getBytes("UTF-8"));

        String auth = "Basic " + encode;
//        System.out.println(auth);

        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .header("Authorization", auth)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .build();

        Response response = client.newCall(request).execute();

        String responseBody = response.body().string();
//        System.out.println(ResponseBody);

        JsonPath jsonPath = new JsonPath(responseBody);
        String token = jsonPath.get("access_token");
        System.out.println("Token" + token);

        client.connectionPool().evictAll();
        return token;

    }
}
