import java.net.*;
import java.util.*;

public class NetworkLogger {
    public static void main(String[] args) throws Exception {
        Enumeration<NetworkInterface> nics = NetworkInterface.getNetworkInterfaces();
        while (nics.hasMoreElements()) {
            NetworkInterface nic = nics.nextElement();
            System.out.println("Interface: " + nic.getDisplayName());
            Enumeration<InetAddress> addrs = nic.getInetAddresses();
            while (addrs.hasMoreElements()) {
                InetAddress addr = addrs.nextElement();
                System.out.println("  -> " + addr.getHostAddress());
            }
        }
    }
}
