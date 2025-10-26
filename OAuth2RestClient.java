// OAuth2RestClient.java
import java.net.http.*;
import java.net.*;
import java.net.http.HttpRequest.BodyPublishers;
import java.time.Duration;

public class OAuth2RestClient {
    public static void main(String[] args) throws Exception {
        String tokenUrl = "https://auth.example.com/oauth2/token";
        String clientId = "your-client-id";
        String clientSecret = "your-secret";

        String body = "grant_type=client_credentials&scope=read";
        HttpRequest tokenReq = HttpRequest.newBuilder(URI.create(tokenUrl))
            .header("Content-Type", "application/x-www-form-urlencoded")
            .header("Authorization", "Basic " + java.util.Base64.getEncoder().encodeToString((clientId+":"+clientSecret).getBytes()))
            .timeout(Duration.ofSeconds(10))
            .POST(BodyPublishers.ofString(body))
            .build();

        HttpClient client = HttpClient.newHttpClient();
        String tokenResp = client.send(tokenReq, HttpResponse.BodyHandlers.ofString()).body();
        // parse access_token (quick and dirty)
        String token = tokenResp.replaceAll(".*\"access_token\"\\s*:\\s*\"([^\"]+)\".*", "$1");
        System.out.println("Token: " + token);

        HttpRequest apiReq = HttpRequest.newBuilder(URI.create("https://api.example.com/me"))
            .header("Authorization", "Bearer " + token)
            .GET().build();
        String apiResp = client.send(apiReq, HttpResponse.BodyHandlers.ofString()).body();
        System.out.println("API response: " + apiResp);
    }
}
