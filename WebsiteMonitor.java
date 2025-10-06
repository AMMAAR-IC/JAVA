// WebsiteMonitor.java
import java.net.*;
import java.util.*;

public class WebsiteMonitor {
    public static void main(String[] args) {
        List<String> sites = Arrays.asList("https://google.com", "https://github.com", "https://nonexist.ai");
        sites.forEach(site -> {
            try {
                HttpURLConnection con = (HttpURLConnection) new URL(site).openConnection();
                con.setConnectTimeout(3000);
                con.connect();
                System.out.println(site + " ✅ " + con.getResponseCode());
            } catch (Exception e) {
                System.out.println(site + " ❌ Offline");
            }
        });
    }
}
