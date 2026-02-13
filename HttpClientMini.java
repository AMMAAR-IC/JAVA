import java.net.*;
import java.io.*;

public class HttpClientMini {
    public static void main(String[] args) throws Exception {
        URL u = new URL("https://example.com");
        HttpURLConnection c = (HttpURLConnection) u.openConnection();
        c.setRequestProperty("User-Agent", "AmmaarBot/1.0");
        c.setRequestProperty("Accept", "text/html");

        BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
        String line;
        while((line=in.readLine())!=null) System.out.println(line);
    }
}
