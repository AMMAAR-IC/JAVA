import java.net.*;
import java.io.*;

public class HttpJsonExample {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://api.github.com/users/AMMAAR-IC");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Accept", "application/json");
        String json = new String(conn.getInputStream().readAllBytes());
        System.out.println("Raw JSON: " + json);
        int idx = json.indexOf("\"followers\"");
        System.out.println("Followers: " + json.substring(idx, idx + 25));
    }
}
