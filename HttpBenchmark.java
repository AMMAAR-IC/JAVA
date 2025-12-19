import java.net.*;

public class HttpBenchmark {
    public static void main(String[] args) throws Exception {
        URL u = new URL("https://example.com");

        long start = System.currentTimeMillis();
        u.openStream().readAllBytes();
        long end = System.currentTimeMillis();

        System.out.println("Response time: " + (end - start) + " ms");
    }
}
