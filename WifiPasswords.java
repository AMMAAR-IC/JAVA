// WifiPasswords.java
import java.io.*;

public class WifiPasswords {
    public static void main(String[] args) throws Exception {
        Process p = Runtime.getRuntime().exec("cat /etc/NetworkManager/system-connections/*");
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        System.out.println("🔑 Saved WiFi Networks & Passwords:");
        while ((line = br.readLine()) != null) {
            if (line.contains("ssid=") || line.contains("psk=")) System.out.println(line);
        }
    }
}
