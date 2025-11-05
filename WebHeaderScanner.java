import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class WebHeaderScanner {

    public static void main(String[] args) {
        try {
            String site = "https://example.com"; // Replace with any URL
            URL url = new URL(site);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            System.out.println("🌍 Scanning: " + site);
            System.out.println("──────────────────────────────");

            // Display basic info
            System.out.println("Response Code: " + connection.getResponseCode());
            System.out.println("Server: " + connection.getHeaderField("Server"));
            System.out.println("Date: " + connection.getHeaderField("Date"));
            System.out.println("Content-Type: " + connection.getHeaderField("Content-Type"));
            System.out.println("Cipher Suite: " + connection.getCipherSuite());

            // Print all headers
            System.out.println("\n📦 All Headers:");
            for (Map.Entry<String, List<String>> entry : connection.getHeaderFields().entrySet()) {
                System.out.println(entry.getKey() + " → " + entry.getValue());
            }

            // Cookies (if any)
            List<String> cookies = connection.getHeaderFields().get("Set-Cookie");
            if (cookies != null) {
                System.out.println("\n🍪 Cookies:");
                for (String cookie : cookies) {
                    System.out.println(cookie);
                }
            } else {
                System.out.println("\n🍪 No cookies found.");
            }

            connection.disconnect();
            System.out.println("\n✅ Scan complete.");

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
