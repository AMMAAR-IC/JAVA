// TinyKVServer.java
import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;

public class TinyKVServer {
    private static final Map<String, String> store = new ConcurrentHashMap<>();
    private static final Path PERSIST = Paths.get("kvstore.txt");

    public static void main(String[] args) throws Exception {
        load();
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/kv", new KVHandler());
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        System.out.println("TinyKVServer running at http://localhost:8080/kv");
    }

    static class KVHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange ex) throws IOException {
            String method = ex.getRequestMethod();
            URI uri = ex.getRequestURI();
            // endpoint: /kv/{key}
            String path = uri.getPath();
            String[] parts = path.split("/", 3);
            String key = parts.length >= 3 ? URLDecoder.decode(parts[2], StandardCharsets.UTF_8.name()) : null;

            try {
                if ("PUT".equalsIgnoreCase(method) && key != null) {
                    String val = new String(ex.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
                    store.put(key, val);
                    persist();
                    send(ex, 200, "OK");
                } else if ("GET".equalsIgnoreCase(method) && key != null) {
                    String val = store.get(key);
                    if (val != null) send(ex, 200, val);
                    else send(ex, 404, "Not Found");
                } else if ("DELETE".equalsIgnoreCase(method) && key != null) {
                    store.remove(key);
                    persist();
                    send(ex, 200, "Deleted");
                } else if ("GET".equalsIgnoreCase(method)) {
                    // list keys
                    send(ex, 200, String.join("\n", store.keySet()));
                } else {
                    send(ex, 405, "Method Not Allowed");
                }
            } catch (Exception e) {
                send(ex, 500, "Error: " + e.getMessage());
            }
        }
    }

    static void send(HttpExchange ex, int code, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().set("Content-Type", "text/plain; charset=utf-8");
        ex.sendResponseHeaders(code, bytes.length);
        try (OutputStream os = ex.getResponseBody()) { os.write(bytes); }
    }

    static void persist() {
        try (BufferedWriter bw = Files.newBufferedWriter(PERSIST, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (Map.Entry<String, String> e : store.entrySet()) {
                bw.write(URLEncoder.encode(e.getKey(), StandardCharsets.UTF_8));
                bw.write('\t');
                bw.write(Base64.getEncoder().encodeToString(e.getValue().getBytes(StandardCharsets.UTF_8)));
                bw.newLine();
            }
        } catch (IOException ignored) {}
    }

    static void load() {
        if (!Files.exists(PERSIST)) return;
        try (BufferedReader br = Files.newBufferedReader(PERSIST)) {
            String line;
            while ((line = br.readLine()) != null) {
                int tab = line.indexOf('\t');
                if (tab > 0) {
                    String k = URLDecoder.decode(line.substring(0, tab), StandardCharsets.UTF_8);
                    String v = new String(Base64.getDecoder().decode(line.substring(tab + 1)), StandardCharsets.UTF_8);
                    store.put(k, v);
                }
            }
        } catch (IOException ignored) {}
    }
}
