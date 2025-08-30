import java.io.*;
import java.net.*;
import java.time.*;

public class MiniHttpEchoServer {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        System.out.println("Starting HTTP Echo Server on port " + port);
        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
                new Thread(new ClientHandler(server.accept())).start();
            }
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket client;
        ClientHandler(Socket client) { this.client = client; }

        @Override
        public void run() {
            try (
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                OutputStream out = client.getOutputStream()
            ) {
                String requestLine = in.readLine();
                System.out.println("Request: " + requestLine);

                // Skip headers
                while (in.ready() && in.readLine().length() > 0) {}

                String body = "<html><body><h1>You asked: " + requestLine + "</h1></body></html>";
                byte[] bytes = body.getBytes();

                out.write(("HTTP/1.1 200 OK\r\n" +
                           "Content-Type: text/html\r\n" +
                           "Content-Length: " + bytes.length + "\r\n" +
                           "\r\n").getBytes());
                out.write(bytes);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try { client.close(); } catch (IOException ignored) {}
            }
        }
    }
}
