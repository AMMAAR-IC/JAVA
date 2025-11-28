import java.net.*;
import java.io.*;

public class PortForwarder {
    public static void main(String[] args) throws Exception {
        int listenPort = 9000;
        String targetHost = "127.0.0.1";
        int targetPort = 8080;

        ServerSocket ss = new ServerSocket(listenPort);
        System.out.println("Forwarding localhost:" + listenPort + " → " + targetHost + ":" + targetPort);

        while (true) {
            Socket client = ss.accept();
            new Thread(() -> handle(client, targetHost, targetPort)).start();
        }
    }

    static void handle(Socket client, String host, int port) {
        try {
            Socket remote = new Socket(host, port);
            pipe(client.getInputStream(), remote.getOutputStream());
            pipe(remote.getInputStream(), client.getOutputStream());
        } catch (Exception ignored) {}
    }

    static void pipe(InputStream in, OutputStream out) {
        new Thread(() -> {
            try { in.transferTo(out); } catch (Exception ignored) {}
        }).start();
    }
}
