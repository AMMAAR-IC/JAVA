import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

public class ReverseProxy {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", (HttpExchange exchange) -> {
            try {
                URL url = new URL("http://example.com" + exchange.getRequestURI());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod(exchange.getRequestMethod());

                InputStream in = conn.getInputStream();
                exchange.sendResponseHeaders(conn.getResponseCode(), conn.getContentLengthLong());

                OutputStream out = exchange.getResponseBody();
                in.transferTo(out);
                out.close();
                in.close();
            } catch (Exception e) {
                String error = "Error: " + e.getMessage();
                exchange.sendResponseHeaders(500, error.length());
                exchange.getResponseBody().write(error.getBytes());
                exchange.close();
            }
        });

        System.out.println("Reverse Proxy running on port 8080...");
        server.start();
    }
}
