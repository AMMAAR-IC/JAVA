import java.io.*;
import java.net.*;
import java.util.regex.*;

public class MetaScraper {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://www.example.com");
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder html = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) html.append(line);
        br.close();

        Pattern p = Pattern.compile("<meta[^>]*>");
        Matcher m = p.matcher(html.toString());
        while (m.find()) {
            System.out.println(m.group());
        }
    }
}
