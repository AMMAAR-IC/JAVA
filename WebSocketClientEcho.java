// WebSocketClientEcho.java
import java.net.http.*;
import java.net.http.WebSocket.*;
import java.net.URI;
import java.util.concurrent.*;

public class WebSocketClientEcho {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        CompletableFuture<Void> done = new CompletableFuture<>();
        client.newWebSocketBuilder().buildAsync(URI.create("wss://echo.websocket.org"), new Listener() {
            public void onOpen(WebSocket w) { System.out.println("Open"); w.sendText("Hello from Java", true); WebSocket.Listener.super.onOpen(w); }
            public CompletionStage<?> onText(WebSocket w, CharSequence msg, boolean last) { System.out.println("Recv: " + msg); done.complete(null); return null; }
            public void onError(WebSocket w, Throwable t) { t.printStackTrace(); }
        });
        done.get(10, TimeUnit.SECONDS);
        System.out.println("Done.");
    }
}
