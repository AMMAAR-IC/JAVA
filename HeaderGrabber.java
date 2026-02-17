import java.net.*;

public class HeaderGrabber {
    public static void main(String[] args) throws Exception {
        URL u = new URL("https://example.com");
        HttpURLConnection c = (HttpURLConnection) u.openConnection();
        c.setRequestMethod("HEAD");

        c.getHeaderFields().forEach((k,v) -> System.out.println(k + " : " + v));
    }
}
