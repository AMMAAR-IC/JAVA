import java.net.*;
import java.io.*;

public class RobotsFetcher {
    public static void main(String[] args) throws Exception {
        String site = "https://example.com";
        URL u = new URL(site + "/robots.txt");

        try(BufferedReader in = new BufferedReader(new InputStreamReader(u.openStream()))){
            String line;
            while((line=in.readLine())!=null)
                System.out.println(line);
        }
    }
}
