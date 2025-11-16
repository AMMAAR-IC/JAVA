import java.net.*;
import java.io.*;

public class HtmlToText {
    public static void main(String[] args) throws Exception {
        String url = "https://example.com";
        String html = new String(new URL(url).openStream().readAllBytes());
        String text = html.replaceAll("<[^>]+>", " ").replaceAll("\\s+", " ");
        System.out.println(text);
    }
}
