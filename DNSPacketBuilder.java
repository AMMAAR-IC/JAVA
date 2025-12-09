import java.net.*;
import java.util.*;

public class DNSPacketBuilder {
    public static void main(String[] args) throws Exception {
        String domain = "example.com";
        byte[] packet = buildQuery(domain);

        DatagramSocket s = new DatagramSocket();
        s.send(new DatagramPacket(packet, packet.length, InetAddress.getByName("8.8.8.8"), 53));

        byte[] buf = new byte[512];
        DatagramPacket resp = new DatagramPacket(buf, buf.length);
        s.receive(resp);

        System.out.println("Response received (" + resp.getLength() + " bytes)");
        s.close();
    }

    static byte[] buildQuery(String d) {
        Random r = new Random();
        byte[] q = new byte[512];

        q[0] = (byte)r.nextInt(256);
        q[1] = (byte)r.nextInt(256);
        q[2] = 1; // standard query
        q[5] = 1; // one question

        int pos = 12;
        for (String part : d.split("\\.")) {
            q[pos++] = (byte)part.length();
            for (char c : part.toCharArray()) q[pos++] = (byte)c;
        }
        q[pos++] = 0;
        q[pos++] = 0; q[pos++] = 1;
        q[pos++] = 0; q[pos++] = 1;

        return Arrays.copyOf(q, pos);
    }
}
