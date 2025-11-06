import java.net.*;
public class DNSResolver {
    public static void main(String[] args) throws Exception {
        String domain = "github.com";
        InetAddress addr = InetAddress.getByName(domain);
        System.out.println(domain + " → " + addr.getHostAddress());
        System.out.println(addr.getHostAddress() + " → " + InetAddress.getByName(addr.getHostAddress()).getHostName());
    }
}
