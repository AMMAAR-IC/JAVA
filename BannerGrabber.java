// BannerGrabber.java
import java.net.*;
import java.io.*;

public class BannerGrabber {
    public static String grab(String host, int port, int timeoutMs) {
        try (Socket s = new Socket()) {
            s.connect(new InetSocketAddress(host, port), timeoutMs);
            s.setSoTimeout(timeoutMs);
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            return br.readLine();
        } catch (Exception e) {
            return null;
        }
    }
    public static void main(String[] args) {
        String b = grab("scanme.nmap.org", 22, 1000);
        System.out.println("Banner: " + b);
    }
}
