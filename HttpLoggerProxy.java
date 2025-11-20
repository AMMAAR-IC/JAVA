// HttpLoggerProxy.java
import java.io.*;
import java.net.*;

public class HttpLoggerProxy {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8888);
        System.out.println("[*] Proxy running on 8888");

        while (true) {
            Socket client = server.accept();
            new Thread(() -> handle(client)).start();
        }
    }

    static void handle(Socket client) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String req = in.readLine();
            if (req == null) return;

            System.out.println(">> " + req);

            String[] parts = req.split(" ");
            String host = parts[1].replace("http://", "").split("/")[0];

            Socket remote = new Socket(host, 80);
            PrintWriter out = new PrintWriter(remote.getOutputStream(), true);
            out.println(req);

            String line;
            while (!(line = in.readLine()).isEmpty()) {
                out.println(line);
            }
            out.println();

            remote.close();
            client.close();
        } catch (Exception ignored) {}
    }
}
