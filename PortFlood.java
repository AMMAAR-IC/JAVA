// PortFlood.java
import java.net.*;
import java.io.*;

public class PortFlood {
    public static void main(String[] args) throws Exception {
        for (int i=0; i<200; i++) {
            new Thread(() -> {
                try {
                    Socket s = new Socket("127.0.0.1", 8080);
                    s.getOutputStream().write("PING\n".getBytes());
                    s.close();
                } catch (Exception ignored) {}
            }).start();
        }
        System.out.println("200 local connections triggered.");
    }
}
