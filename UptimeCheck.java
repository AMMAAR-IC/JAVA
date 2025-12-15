import java.net.HttpURLConnection;
import java.net.URL;

public class UptimeCheck {
    public static void main(String[] args) throws Exception {
        URL u = new URL("https://example.com");
        HttpURLConnection c = (HttpURLConnection) u.openConnection();
        c.setConnectTimeout(3000);
        System.out.println("Status: " + c.getResponseCode());
    }
}
