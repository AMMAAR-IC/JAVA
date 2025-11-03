// SimpleWsServer.java
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/echo")
public class SimpleWsServer {
    @OnOpen public void open(Session s) { System.out.println("Open: " + s.getId()); }
    @OnMessage public void onMessage(Session s, String m) throws IOException { s.getBasicRemote().sendText("Echo: " + m); }
    @OnClose public void close(Session s) { System.out.println("Close: " + s.getId()); }
    @OnError public void err(Session s, Throwable t) { t.printStackTrace(); }
}
