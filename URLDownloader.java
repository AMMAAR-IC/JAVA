import java.net.URL;
import java.nio.file.*;

public class URLDownloader {

    public static void main(String[] args) throws Exception {

        URL url=new URL("https://example.com");

        byte[] data=url.openStream().readAllBytes();

        Files.write(Path.of("download.html"),data);

        System.out.println("Downloaded");
    }
}
