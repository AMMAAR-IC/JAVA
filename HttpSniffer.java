// HttpSniffer.java
import java.net.*;
import java.io.*;

public class HttpSniffer {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8080);
        System.out.println("Sniffer started on port 8080...");
        while (true) {
            Socket client = server.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String line;
            while ((line = in.readLine()) != null && !line.isEmpty())
                System.out.println(line);
            client.close();
        }
    }
}
