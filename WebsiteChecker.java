import java.net.*;
import java.util.*;

public class WebsiteChecker {
    public static void main(String[] args) {
        List<String> sites = List.of("https://www.google.com", "https://github.com", "https://invalid-site.com");
        for (String site : sites) {
            try {
                HttpURLConnection con = (HttpURLConnection) new URL(site).openConnection();
                con.setRequestMethod("HEAD");
                System.out.println(site + " → " + con.getResponseCode());
            } catch (Exception e) {
                System.out.println(site + " → DOWN");
            }
        }
    }
}
