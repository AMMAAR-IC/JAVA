// Http2Client.java
import java.net.http.*;
import java.net.URI;
import java.time.Duration;

public class Http2Client {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
        HttpRequest req = HttpRequest.newBuilder(URI.create("https://http2.golang.org"))
                .timeout(Duration.ofSeconds(5)).GET().build();
        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status: " + res.statusCode());
        System.out.println(res.body().substring(0, Math.min(500, res.body().length())));
    }
}
