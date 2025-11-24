// LatencyMap.java
import java.net.*;
import java.util.*;

public class LatencyMap {
    public static void main(String[] args) {
        List<String> urls = List.of("https://google.com","https://github.com","https://openai.com");
        urls.forEach(u -> {
            try {
                long t = System.currentTimeMillis();
                HttpURLConnection c = (HttpURLConnection)new URL(u).openConnection();
                c.setConnectTimeout(3000);
                c.connect();
                System.out.println(u + " → " + (System.currentTimeMillis()-t) + " ms");
            } catch(Exception e){ System.out.println(u + " → DOWN"); }
        });
    }
}
