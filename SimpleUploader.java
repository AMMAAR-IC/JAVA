// SimpleUploader.java
import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.nio.file.*;

public class SimpleUploader {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8085), 0);
        server.createContext("/upload", exch -> {
            if (!"POST".equalsIgnoreCase(exch.getRequestMethod())) { exch.sendResponseHeaders(405, -1); return; }
            Path out = Paths.get("uploads");
            Files.createDirectories(out);
            // read whole request body (assumes small test files)
            byte[] body = exch.getRequestBody().readAllBytes();
            Path file = out.resolve("upload_" + System.currentTimeMillis());
            Files.write(file, body);
            String resp = "Saved: " + file.toString();
            exch.sendResponseHeaders(200, resp.length());
            exch.getResponseBody().write(resp.getBytes());
            exch.close();
        });
        server.start();
        System.out.println("Uploader listening on http://localhost:8085/upload");
    }
}
