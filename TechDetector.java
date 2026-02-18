import java.net.*;

public class TechDetector {
    public static void main(String[] args) throws Exception {
        HttpURLConnection c = (HttpURLConnection)new URL("https://example.com").openConnection();
        String server = c.getHeaderField("Server");
        String powered = c.getHeaderField("X-Powered-By");

        System.out.println("Server: " + server);
        System.out.println("Powered: " + powered);
    }
}
