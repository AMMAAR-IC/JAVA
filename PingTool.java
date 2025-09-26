import java.net.InetAddress;

public class PingTool {
    public static void main(String[] args) {
        try {
            String host = "8.8.8.8"; // Google DNS
            InetAddress inet = InetAddress.getByName(host);
            if (inet.isReachable(3000)) {
                System.out.println(host + " is reachable");
            } else {
                System.out.println(host + " is not reachable");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
