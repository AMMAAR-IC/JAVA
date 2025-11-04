import java.net.http.*;
import java.net.URI;

public class IPInfo {
    public static void main(String[] args) throws Exception {
        HttpClient c = HttpClient.newHttpClient();
        HttpRequest r = HttpRequest.newBuilder().uri(URI.create("https://ipinfo.io/json")).build();
        HttpResponse<String> res = c.send(r, HttpResponse.BodyHandlers.ofString());
        System.out.println(res.body());
    }
}
