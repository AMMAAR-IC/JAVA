import java.net.*;

public class UDPFlood {
    public static void main(String[] args) throws Exception {
        DatagramSocket s = new DatagramSocket();
        byte[] payload = "AMMAAR".getBytes();

        InetAddress ip = InetAddress.getByName("127.0.0.1");
        int port = 9999;

        for(int i=0;i<500;i++){
            DatagramPacket p = new DatagramPacket(payload, payload.length, ip, port);
            s.send(p);
        }
        System.out.println("Sent 500 packets");
    }
}
