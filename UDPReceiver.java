import java.net.*;

public class UDPReceiver {
    public static void main(String[] args) throws Exception {
        DatagramSocket s = new DatagramSocket(9999);
        byte[] buf = new byte[1024];

        while(true){
            DatagramPacket p = new DatagramPacket(buf, buf.length);
            s.receive(p);
            System.out.println(new String(p.getData(),0,p.getLength()));
        }
    }
}
