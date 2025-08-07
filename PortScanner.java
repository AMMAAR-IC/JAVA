import java.net.*;

public class PortScanner {
    public static void main(String[] args) {
        String host = "127.0.0.1"; // Change to scan other LAN IPs
        System.out.println("🔍 Scanning ports on " + host + "...");

        for (int port = 1; port <= 1024; port++) {
            try (Socket socket = new Socket()) {
                socket.connect(new InetSocketAddress(host, port), 200);
                System.out.println("✅ Open: " + port);
            } catch (Exception ignored) {}
        }
    }
}
