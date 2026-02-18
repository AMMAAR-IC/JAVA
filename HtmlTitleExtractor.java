import java.net.*;
import java.util.regex.*;

public class HtmlTitleExtractor {
    public static void main(String[] args) throws Exception {
        String html = new String(new URL("https://example.com").openStream().readAllBytes());
        Matcher m = Pattern.compile("<title>(.*?)</title>", Pattern.CASE_INSENSITIVE).matcher(html);
        if(m.find()) System.out.println(m.group(1));
    }
}
