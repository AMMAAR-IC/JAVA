import java.net.*;

public class URLNormalizer {
    public static void main(String[] args) throws Exception {
        String base = "https://example.com/docs/";
        String link = "../index.html";

        URL full = new URL(new URL(base), link);
        System.out.println(full.toString());
    }
}
