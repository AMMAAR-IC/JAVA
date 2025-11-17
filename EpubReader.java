import java.util.zip.*;
import java.io.*;

public class EpubReader {
    public static void main(String[] args) throws Exception {
        ZipFile epub = new ZipFile("book.epub");
        epub.stream().forEach(entry -> {
            if (entry.getName().endsWith(".xhtml") || entry.getName().endsWith(".html")) {
                try (InputStream is = epub.getInputStream(entry)) {
                    String html = new String(is.readAllBytes());
                    String text = html.replaceAll("<[^>]+>", " ");
                    System.out.println(text);
                } catch (Exception ignored) {}
            }
        });
    }
}
