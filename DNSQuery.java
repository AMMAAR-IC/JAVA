// DNSQuery.java
import java.net.*;
import java.util.*;

public class DNSQuery {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        byte[] packet = build("google.com");

        DatagramPacket req = new DatagramPacket(packet, packet.length, InetAddress.getByName("8.8.8.8"), 53);
        socket.send(req);

        byte[] buf = new byte[512];
        DatagramPacket res = new DatagramPacket(buf, buf.length);
        socket.receive(res);

        System.out.println("Received DNS Response (" + res.getLength() + " bytes)");
        socket.close();
    }

    static byte[] build(String domain) {
        Random r = new Random();
        byte[] out = new byte[512];
        out[0] = (byte)r.nextInt(256);
        out[1] = (byte)r.nextInt(256);
        out[2] = 1; out[3] = 0;
        out[4] = 0; out[5] = 1;
        out[6]=out[7]=out[8]=out[9]=out[10]=out[11]=0;

        int pos = 12;
        for (String part : domain.split("\\.")) {
            out[pos++] = (byte)part.length();
            for (char c : part.toCharArray()) out[pos++] = (byte)c;
        }
        out[pos++] = 0;

        out[pos++] = 0;
        out[pos++] = 1;
        out[pos++] = 0;
        out[pos++] = 1;

        return Arrays.copyOf(out, pos);
    }
}
