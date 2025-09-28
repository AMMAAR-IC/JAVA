// URLHealthChecker.java
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class URLHealthChecker {
    public static void main(String[] args) throws Exception {
        List<String> urls = Arrays.asList(
            "https://example.com", "https://www.google.com", "https://www.github.com"
        );

        ExecutorService ex = Executors.newFixedThreadPool(8);
        for (String u : urls) {
            ex.submit(() -> check(u));
        }
        ex.shutdown();
        ex.awaitTermination(2, TimeUnit.MINUTES);
    }

    static void check(String urlStr) {
        try {
            long start = System.nanoTime();
            HttpURLConnection con = (HttpURLConnection)new URL(urlStr).openConnection();
            con.setConnectTimeout(3000);
            con.setReadTimeout(3000);
            con.setRequestMethod("GET");
            con.connect();
            int code = con.getResponseCode();
            long timeMs = (System.nanoTime() - start) / 1_000_000;
            System.out.printf("%s -> %d (%d ms)%n", urlStr, code, timeMs);
        } catch (Exception e) {
            System.out.printf("%s -> ERROR (%s)%n", urlStr, e.getMessage());
        }
    }
}
