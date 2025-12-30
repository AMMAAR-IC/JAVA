import java.net.*;
import java.nio.file.*;

public class WebArchiver {
    public static void main(String[] args)throws Exception{
        String url="https://example.com";
        String html=new String(new URL(url).openStream().readAllBytes());
        Files.writeString(Path.of("page.html"),html);
        System.out.println("Archived!");
    }
}
