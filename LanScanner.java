import java.net.*;

public class LanScanner {
    public static void main(String[] args) throws Exception {
        for(int i=1;i<=255;i++){
            String host="192.168.1."+i;
            if(InetAddress.getByName(host).isReachable(200))
                System.out.println("Active: "+host);
        }
    }
}
